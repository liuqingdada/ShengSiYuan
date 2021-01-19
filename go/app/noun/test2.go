package main

import "fmt"

func main() {
	var s [2]string
	s[0] = "Hello"
	s[1] = "World"
	fmt.Println(s[0], s[1])
	fmt.Println(s)

	primes := [6]int{2, 3, 5, 7, 11, 13}
	fmt.Println(primes)

	var sa []int = primes[1:4]
	fmt.Println(sa)

	names := [4]string{
		"John",
		"Paul",
		"George",
		"Ringo",
	}
	fmt.Println(names)

	a := names[0:2]
	b := names[1:3]
	fmt.Println(a, b)

	b[0] = "XXX"
	fmt.Println(a, b)
	fmt.Println(names)

	q := []int{2, 3, 5, 7, 11, 13}
	fmt.Println(q)

	r := []bool{true, false, true, true, false, true}
	fmt.Println(r)

	st := []struct {
		i int
		b bool
	}{
		{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true},
	}
	fmt.Println(st)

	// 切片下界的默认值为 0，上界则是该切片的长度。
	st1 := st[1:4]
	fmt.Println(st1)

	st1 = st[:2]
	fmt.Println(st1)

	st1 = st[1:]
	fmt.Println(st1)

	printSlice(q)
	// 截取切片使其长度为 0
	q = q[:0]
	printSlice(q)

	// 拓展其长度
	q = q[:4]
	printSlice(q)

	// 舍弃前两个值
	q = q[2:]
	printSlice(q)

	var si []int
	printSlice(si)
	if si == nil {
		fmt.Println("nil")
		fmt.Printf("%T\n", si)
	}
}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}
