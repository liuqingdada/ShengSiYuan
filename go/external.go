package main

import "fmt"
import "rsc.io/quote"

func main() {
  fmt.Println("external main:")
  fmt.Println(quote.Go())
}
