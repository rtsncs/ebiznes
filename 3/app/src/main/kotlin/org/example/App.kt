package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.serialization.*
import kotlinx.serialization.json.*

var socket: DefaultClientWebSocketSession? = null
var seq: Int? = null
var interval: Long = 0
val format = Json { ignoreUnknownKeys = true }
val client =
        HttpClient(CIO) {
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(format)
            }
        }

val categories =
        mapOf(
                "Category 1" to listOf("Product 1", "Product 2", "Product 3"),
                "Category 2" to listOf("Product 4", "Product 5", "Product 6"),
        )

fun main() {
    if (System.getenv("DISCORD_TOKEN") == null) {
        println("DISCORD_TOKEN environment variable not set")
        return
    }

    runBlocking {
        socket =
                client.webSocketSession(
                        method = HttpMethod.Get,
                        host = "gateway.discord.gg",
                        path = "?v=10&encoding=json"
                )
        while (true) {
            socket!!.incoming.consumeAsFlow().collect {
                when (it) {
                    is Frame.Close -> {
                        println("Connection closed")
                        println(it.readReason())
                        return@collect
                    }
                    is Frame.Text -> {
                        val gateway_message =
                                format.decodeFromString<DiscordGatewayMessage>(it.readText())
                        seq = gateway_message.s
                        when (gateway_message.op) {
                            0 -> {
                                launch { handle_dispatch(gateway_message) }
                            }
                            10 -> {
                                println("Hello received")
                                interval =
                                        gateway_message.d?.jsonObject?.get("heartbeat_interval")
                                                ?.jsonPrimitive
                                                ?.long!!
                                identify()
                            }
                            11 -> {
                                println("Heartbeat ACK")
                            }
                            else -> {
                                println("Unknown op code: ${gateway_message.op}")
                            }
                        }
                    }
                    else -> println(it)
                }
            }
        }
    }
}

@Serializable
data class DiscordGatewayMessage(var op: Int, var d: JsonElement?, var t: String?, var s: Int?)

@Serializable
data class DiscordMessage(
        var id: String,
        var channel_id: String,
        var content: String,
)

@Serializable
data class DiscordMessageRequest(
        var content: String,
        var message_reference: DiscordMessageReference?
)

@Serializable data class DiscordMessageReference(var message_id: String, var type: Int)

suspend fun identify() {
    socket!!.sendSerialized(
            DiscordGatewayMessage(
                    2,
                    buildJsonObject {
                        put("token", System.getenv("DISCORD_TOKEN"))
                        put("intents", 33280)
                        putJsonObject("properties") {}
                    },
                    null,
                    null
            )
    )
    println("Identify sent")
}

suspend fun send_heartbeat(seq: Int?) {
    socket!!.sendSerialized(DiscordGatewayMessage(1, JsonPrimitive(seq), null, null))
    println("Heartbeat sent")
}

suspend fun send_message(
        client: HttpClient,
        content: String,
        channel_id: String,
        reply_to: String
) {
    val response =
            client.post("https://discord.com/api/v10/channels/${channel_id}/messages") {
                headers {
                    append("Authorization", "Bot ${System.getenv("DISCORD_TOKEN")}")
                    append("Content-Type", "application/json")
                }
                setBody(
                        Json.encodeToString(
                                DiscordMessageRequest(content, DiscordMessageReference(reply_to, 0))
                        )
                )
            }
    if (response.status == HttpStatusCode.OK) {
        println("Message sent")
    } else {
        println("Failed to send message: ${response.status}")
    }
}

suspend fun handle_dispatch(gateway_message: DiscordGatewayMessage) = coroutineScope {
    when (gateway_message.t) {
        "READY" -> {
            println("Ready received")
            launch {
                while (true) {
                    send_heartbeat(seq)
                    delay(interval)
                }
            }
        }
        "MESSAGE_CREATE" -> {
            val message = format.decodeFromJsonElement<DiscordMessage>(gateway_message.d!!)
            println("Message received: ${message.content}")
            if (message.content.startsWith("!categories")) {
                launch {
                    send_message(
                            client,
                            categories.keys.joinToString("\n"),
                            message.channel_id,
                            message.id
                    )
                }
            } else if (message.content.startsWith("!products")) {
                val category = message.content.removePrefix("!products").trim()
                val content =
                        if (category.isEmpty()) {
                            "You must provide a category"
                        } else if (categories.containsKey(category)) {
                            categories[category]!!.joinToString("\n")
                        } else {
                            "Category not found"
                        }
                launch { send_message(client, content, message.channel_id, message.id) }
            } else {}
        }
        else -> println("Unknown event: ${gateway_message.t}")
    }
}
