package main

import (
	"log"
	"math/rand"
	"os"
	"runtime/pprof"
	"time"
)

const (
	row = 10000
	col = 10000
)

func fillMatrix(m *[row][col]int) {
	s := rand.New(rand.NewSource(time.Now().UnixNano()))

	for i := 0; i < row; i++ {
		for j := 0; j < col; j++ {
			m[i][j] = s.Intn(100000)
		}
	}
}

func calculate(m *[row][col]int) {
	for i := 0; i < row; i++ {
		tmp := 0
		for j := 0; j < col; j++ {
			tmp += m[i][j]
		}
	}
}

func main() {
	f, err := os.Create("cpu.prof")
	if err != nil {
		log.Fatal("couldn't create CPU profile: ", err)
	}
	defer f.Close()

	if err := pprof.StartCPUProfile(f); err != nil {
		log.Fatal("couldn't start CPU profile: ", err)
	}
	defer pprof.StopCPUProfile()

	x := [row][col]int{}
	fillMatrix(&x)
	calculate(&x)

	f2, err := os.Create("mem.prof")
	if err != nil {
		log.Fatal("couldn't create Mem profile: ", err)
	}
	defer f2.Close()

	if err := pprof.WriteHeapProfile(f2); err != nil {
		log.Fatal("couldn't start Mem profile: ", err)
	}
}
