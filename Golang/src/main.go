package main

import (
	"fmt"
)

func Print(args ...int) (x, y int) {
	fmt.Println(len(args))
	sum := 0
	for _, data := range args[1:] {
		sum += data
	}
	x = sum
	y = sum
	return
}

func Sum(n int) (sum int) {
	if n == 0 {
		return 0
	} else {
		return Sum(n - 1) + n
	}
}

func Sort(arr []int) {
	arr = append(arr, 1, 2, 3, 4)
	sum := 0
	for _, data := range arr {
		sum += data
	}
	fmt.Println(sum)
}

func main () {
	//var a []int = []int{1, 2, 3, 4, 5}
	//fmt.Println(cap(a))
	//slice := make([]int, 4, 10)
	//copy(slice, a)
	//Sort(a)
	//fmt.Println(a)

	var m map[string]string = map[string]string{}
	m["a"] = "A"
	delete(m, "a")
	delete(m, "a")
	fmt.Println(m["b"] == "")

	//userinfo.Userlogin()
	//test()
	//fmt.Println(Sum(10))
	//fmt.Println("hello world")
	//f := Print
	//fmt.Println(f(1, 2, 3, 4))
	//fmt.Printf("%T", f)
}
