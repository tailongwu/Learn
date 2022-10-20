package main

import (
	"fmt"
	"reflect"
)

const (
	_          = iota
	KB float64 = 1 << (10 * iota)
	MB
)

type P struct {
	A int
	B string
	C float64
}

func main() {
	p := P{1, "2", 3.0}
	v := reflect.ValueOf(p)
	fmt.Println(v.Field(1).Elem().CanSet())
	fmt.Printf()
}
