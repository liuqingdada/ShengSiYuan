package main

import(
  "fmt"
  "shengsiyuan.go.com/greetings"
)

func main() {
  message := greetings.Hello("Cooper")
  fmt.Println(message)
}
