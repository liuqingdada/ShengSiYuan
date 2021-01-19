package main

import "fmt"

type Student struct {
	Lat, Long float64
}

func main() {
	var m map[string]Student
	m = make(map[string]Student)
	m["Bell Labs"] = Student{
		40.48433,
		-74.39967,
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
}
