package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"time"

	"github.com/labstack/echo/v4"
	"github.com/labstack/gommon/log"
)

type Message struct {
	Role    string `json:"role"`
	Content string `json:"content"`
}

type ChatRequest struct {
	Messages []Message `json:"messages"`
}

type ChatResponse struct {
	Message Message `json:"message"`
}

type OllamaRequest struct {
	Model    string    `json:"model"`
	Messages []Message `json:"messages"`
	Stream   bool      `json:"stream"`
}

type OllamaResponse struct {
	Model              string    `json:"model"`
	CreatedAt          time.Time `json:"created_at"`
	Message            Message   `json:"message"`
	Done               bool      `json:"done"`
	TotalDuration      int64     `json:"total_duration"`
	LoadDuration       int64     `json:"load_duration"`
	PromptEvalCount    int       `json:"prompt_eval_count"`
	PromptEvalDuration int64     `json:"prompt_eval_duration"`
	EvalCount          int       `json:"eval_count"`
	EvalDuration       int64     `json:"eval_duration"`
}

const ollama_url = "http://localhost:11434"

func main() {
	e := echo.New()
	e.Logger.SetLevel(log.DEBUG)
	e.GET("/", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello")
	})

	e.POST("/message", func(c echo.Context) error {
		req := ChatRequest{}
		if err := c.Bind(&req); err != nil {
			return echo.NewHTTPError(http.StatusBadRequest, nil)
		}

		postBody, _ := json.Marshal(OllamaRequest{
			Model:    "llama3",
			Messages: req.Messages,
			Stream:   false,
		})
		ollamaResponse, err := http.Post(ollama_url+"/api/chat", "application/json", bytes.NewBuffer(postBody))
		if err != nil {
			return err
		}
		defer ollamaResponse.Body.Close()
		rawBody, err := io.ReadAll(ollamaResponse.Body)
		if err != nil {
			return err
		}
		ollamaResponseBody := OllamaResponse{}
		if err := json.Unmarshal(rawBody, &ollamaResponseBody); err != nil {
			return err
		}

		fmt.Printf("%s\n", rawBody)
		fmt.Printf("%v+\n", ollamaResponseBody)

		response := ChatResponse{
			Message: ollamaResponseBody.Message,
		}
		return c.JSON(http.StatusOK, response)
	})

	e.Logger.Fatal(e.Start(":8080"))
}
