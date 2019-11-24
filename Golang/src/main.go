package main

import (
	"fmt"
	"userinfo"
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

func main () {
	userinfo.Userlogin()
	test()
	fmt.Println(Sum(10))



	fmt.Println("hello world")
	f := Print
	fmt.Println(f(1, 2, 3, 4))
	fmt.Printf("%T", f)
}
