package main

import (
	"fmt"
	"runtime"
	"time"
)

/*
	Go 只运行选定的 case，而非之后所有的 case。 实际上，Go 自动提供了在这些语言中每个 case 后面所需的 break 语句。
	除非以 fallthrough 语句结束，否则分支会自动终止。 Go 的另一点重要的不同在于 switch 的 case 无需为常量，
	且取值不必为整数
*/
func main() {
	// defer 语句会将函数推迟到外层函数返回之后执行。
	// 推迟调用的函数其参数会立即求值，但直到外层函数返回前该函数都不会被调用。
	// 推迟的函数调用会被压入一个栈中。当外层函数返回时，被推迟的函数会按照后进先出的顺序调用。
	defer fmt.Println("world")
	for i := 0; i < 10; i++ {
		defer fmt.Println(i)
	}

	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X, ", os)
	case "linux":
		fmt.Println(os)
	default:
		fmt.Printf("%s.\n", os)
	}

	t := time.Now()
	switch {
	case t.Hour() < 12:
		fmt.Println("Good morning!")
	case t.Hour() < 17:
		fmt.Println("Good afternoon.")
	default:
		fmt.Println("Good evening.")
	}
}
