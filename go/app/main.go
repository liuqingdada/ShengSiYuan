// package main表示一个可独立执行的程序，每个 Go 应用程序都包含一个名为 main 的包
package main

import (
	"fmt" // 格式化 IO（输入/输出）
	"math"
	"math/rand"
	"time"
)

func add(a int, b int) int {
	return a + b
}

func add2(x, y int) int {
	return x + y
}

func swap(x, y string) (string, string) {
	return x + y, y + x
}

func split(sum int) (x, y int) {
	x = sum*4 - 9
	y = sum - x
	return
}

var c, python, java bool
var j, k int = 1, 2

/*
 * func main() 是程序开始执行的函数。main 函数是每一个可执行程序所必须包含的，
 * 一般来说都是在启动后第一个执行的函数（如果有 init() 函数则会先执行该函数）
 */
func init() {
	fmt.Println("init run: ")
}

/*
 * 当标识符（包括常量、变量、类型、函数名、结构字段等等）以一个大写字母开头，
 * 如：Group1，那么使用这种形式的标识符的对象就可以被外部包的代码所使用（客户端程序需要先导入这个包）
 * 这被称为导出（像面向对象语言中的 public）
 * 标识符如果以小写字母开头，则对包外是不可见的，但是他们在整个包的内部是可见并且可用的（像面向对象语言中的 protected ）
 */
//goland:noinspection GoBoolExpressions
func main() {
	fmt.Println("main run: ")

	rand.Seed(time.Now().UnixNano())
	fmt.Println("gen a random num is: ", rand.Intn(10000))

	fmt.Println(math.Pi)
	fmt.Println(add(1, 2))
	fmt.Println(swap("hello", "cooper"))
	fmt.Println(split(9))

	var i int
	fmt.Println(i, c, python, java)

	var m, n, p = true, false, "no"
	fmt.Println(j, k, m, n, p)

	// := 结构不能在函数外使用
	k := 23
	o, q, r := true, false, "no"
	fmt.Println(k, o, q, r)
}
