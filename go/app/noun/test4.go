package main

import (
	"fmt"
	"strings"
)

type Student struct {
	Lat, Long float64
	name      string
}

func main() {
	var m map[string]Student
	m = make(map[string]Student)
	m["Bell Labs"] = Student{
		40.48433,
		-74.39967,
		"suhen",
	}
	fmt.Println(m["Bell Labs"])

	m = map[string]Student{
		"Bell": {
			Lat: -10.111,
		},
		"Labs": {
			Long: 99.11,
		},
	}
	fmt.Println(m)

	delete(m, "Bell")
	elem, ok := m["Bell"]
	fmt.Println(elem, ok)
	fmt.Printf("Fields are: %q\n", strings.Fields("  foo bar  baz   "))
}
