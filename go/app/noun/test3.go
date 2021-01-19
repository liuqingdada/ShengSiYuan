package main

import "fmt"

func printInfo(s string, x []int) {
	fmt.Printf("%s len=%d cap=%d %v\n", s, len(x), cap(x), x)
}

func main() {
	a := make([]int, 5)
	printInfo("a", a)

	b := make([]int, 0, 5)
	printInfo("b", b)

	c := b[:2]
	printInfo("c", c)

	d := c[2:5]
	printInfo("d", d)

	var s []int
	printInfo("s", s)
	s = append(s, 0)
	printInfo("s", s)
	s = append(s, 1)
	printInfo("s", s)
	s = append(s, 2, 3, 4)
	printInfo("s", s)

	var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
	for i, v := range pow {
		fmt.Printf("2^%d = %d\n", i, v)
	}
	pow2 := make([]int, 10)
	for i := range pow2 {
		pow2[i] = 1 << uint(i)
	}
	for _, v := range pow2 {
		fmt.Printf("%d\n", v)
	}
}
