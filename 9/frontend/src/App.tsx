import { useEffect, useRef, useState } from "react";
import "./App.css";
import axios from "axios";

interface Message {
  role: "user" | "assistant";
  content: string;
}

function App() {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState<Message[]>([]);

  const sendMessage = async () => {
    if (!input) return;

    const newMessages: Message[] = [
      ...messages,
      { role: "user", content: input },
    ];
    setMessages(newMessages);
    setInput("");

    try {
      const response = await axios.post("http://localhost:8080/message", {
        messages: newMessages,
      });
      const botResponse = response.data.message;

      setMessages((messages) => [...messages, botResponse]);
    } catch (err) {
      console.error(err);
    }
  };

  const messagesEndRef = useRef<HTMLDivElement | null>(null);
  const scrollToBottom = () =>
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  return (
    <>
      <h1>Chatbot</h1>
      <div
        id="chatbox"
        style={{
          border: "1px solid #ccc",
          padding: "10px",
          height: "300px",
          overflowY: "scroll",
        }}
      >
        {messages.map((message, index) => (
          <p
            key={index}
            style={{ textAlign: message.role === "user" ? "right" : "left" }}
          >
            <strong>{message.role === "user" ? "You" : "Bot"}:</strong>{" "}
            {message.content}
          </p>
        ))}
        <div ref={messagesEndRef} />
      </div>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Enter a message..."
      />
      <button onClick={sendMessage}>Send</button>
    </>
  );
}

export default App;
