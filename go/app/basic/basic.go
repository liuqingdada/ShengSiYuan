package main

import (
	"fmt"
	"math"
)

/*
	int, uint 和 uintptr 在 32 位系统上通常为 32 位宽，在 64 位系统上则为 64 位宽
	当你需要一个整数值时应使用 int 类型，除非你有特殊的理由使用固定大小或无符号的整数类型

	没有明确初始值的变量声明会被赋予它们的零值, 零值是：
	数值类型为 0
	布尔类型为 false
	字符串为 ""（空字符串）
*/
func main() {
	const b bool = false
	const s string = "str"
	const i int = 0
	const i8 int8 = 0x7F
	const i16 int16 = 0x7FFF
	const i32 int32 = 0x7FFFFFFF
	const i64 int64 = 0x7FFFFFFFFFFFFFFF
	fmt.Println(b, s, i, i8, i16, i32, i64)

	const ui uint = 0
	const ui8 uint8 = 0xFF
	const ui16 uint16 = 0xFFFF
	const ui32 uint32 = 0xFFFFFFFF
	const ui64 uint64 = 0xFFFFFFFFFFFFFFFF
	const ptr uintptr = 0
	fmt.Println(ui, ui8, ui16, ui32, ui64, ptr)

	const by byte = 0xFF       // uint8 的别名
	const ru rune = 0x7FFFFFFF // int32 的别名
	const f32 float32 = 0xFFFFFFFF
	const f64 float64 = 0.0
	fmt.Println(by, ru, f32, f64)

	const maxInt uint64 = 1<<64 - 1
	fmt.Println(maxInt)

	var x, y int = 3, 4
	var f float64 = math.Sqrt(float64(x*x + y*y))
	var z uint = uint(f)
	fmt.Println(x, y, f, z)

	m := 52
	n := float64(m)
	o := uint(n)
	fmt.Println(m, n, o)
}
