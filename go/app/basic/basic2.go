package main

import (
	"fmt"
	"time"
)

func main() {
	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println(sum)

	for ; sum < 1000; {
		sum += 1
	}
	fmt.Println(sum)
	for {
		fmt.Println(time.Now())
	}
}
