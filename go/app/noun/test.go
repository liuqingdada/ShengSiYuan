package main

import "fmt"

// 一个结构体（struct）就是一组字段（field）。
type Vertex struct {
	X int
	Y int
	Z int
}

func main() {
	var p *int
	fmt.Println(p)

	i := 42
	p = &i
	fmt.Println(*p)
	*p = 21
	fmt.Println(i)

	*p = *p / 3
	fmt.Println(i)

	fmt.Println(Vertex{1, 2, 3})
	v := Vertex{1, 2, 3}
	v.X = 10
	fmt.Println(v)

	// 如果我们有一个指向结构体的指针 p，那么可以通过 (*p).X 来访问其字段 X。
	//不过这么写太啰嗦了，所以语言也允许我们使用隐式间接引用，直接写 p.X 就可以。
	var vp = &v
	(*vp).Y = 100
	fmt.Println(v)

	vp.Z = 100
	fmt.Println(v)

	var (
		v1 = Vertex{1, 2, 3}
		v2 = Vertex{X: 1}
		v3 = Vertex{}
		p2 = &Vertex{11, 22, 33}
	)
	fmt.Println(v1, p2, v2, v3)
}
