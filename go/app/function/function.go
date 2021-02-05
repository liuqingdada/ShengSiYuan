package main

import (
	"fmt"
	"math"
)

type Vertex struct {
	x, y float64
}

// Go 没有类。不过你可以为结构体类型定义方法。
// 方法就是一类带特殊的 接收者 参数的函数。
// 方法接收者在它自己的参数列表内，位于 func 关键字和方法名之间。
func (v Vertex) Abs() float64 {
	return math.Sqrt(v.x*v.x + v.y*v.y)
}

// 方法即函数
// 记住：方法只是个带接收者参数的函数。
// 现在这个 Abs 的写法就是个正常的函数，功能并没有什么变化。
func Abs(v Vertex) float64 {
	return math.Sqrt(v.x*v.x + v.y*v.y)
}

type long float64

func (l long) Abs() long {
	if l < 0 {
		return -l
	}
	return l
}

func main() {
	v := Vertex{3, 4}
	fmt.Println(v.Abs())

	l := long(-math.Sqrt(2))
	fmt.Println(l.Abs())
}
