package main

import (
	"fmt"
	"reflect"
	"sync"
	"time"
)

// 例子：存在并发问题，count++不是原子操作
//func main() {
//	count := 0
//	var wg sync.WaitGroup
//	wg.Add(10)
//	for i := 0; i < 10; i++ {
//		go func() {
//			defer wg.Done()
//			for j := 0; j < 10000; j++ {
//				count++
//			}
//		}()
//	}
//	wg.Wait()
//	fmt.Println(count)
//}

type RWMap struct {
	sync.RWMutex
	m map[int]int
}

func NewRWMap(n int) *RWMap {
	return &RWMap{
		m: make(map[int]int, n),
	}
}

func (m *RWMap) Get(key int) (int, bool) {
	m.RLock()
	defer m.RUnlock()
	val, ok := m.m[key]
	return val, ok
}

func (m *RWMap) Set(key, value int) {
	m.Lock()
	defer m.Unlock()
	m.m[key] = value
}

type Mutex struct {
	ch chan struct{}
}

func NewMutex() *Mutex {
	 mu := &Mutex{ch: make(chan struct{}, 1)}
	 return mu
}

func (m *Mutex) Lock() {
	m.ch <- struct{}{}
}

func (m *Mutex) Unlock() {
	<- m.ch
}

func (m *Mutex) TryLock() bool {
	select {
	case m.ch <- struct{}{}:
		return true
	default:
		return false
	}
}

func T(ch chan <- int) {
	ch <- 1
	fmt.Println("xxx")
}

func main() {
	slice := make([]string, 0, 5)
	for i := 0; i < 5; i++ {
		slice = append(slice, "xxx")
	}
	
	fmt.Println(len(slice))
	fmt.Println(slice)

	//ch := make(chan int)
	//go func() {
	//	T(ch)
	//}()
	//for {
	//	v := <-ch
	//	fmt.Println(v)
	//}
	//ErrGroup
}

func NewWorker(id int, ch chan string, next chan string) {
	for {
		t := <- ch
		fmt.Println(id + 1)
		time.Sleep(1e6)
		next <- t
	}
}

func pump2(ch chan int) {
	for i := 0; ; i++ {
		ch <- i
	}
}

func pump1(ch chan int) {
	for i := 0; ; i++ {
		ch <- i * 2;
	}
}

func createCases(chs ...chan int) []reflect.SelectCase {
	var cases []reflect.SelectCase
	for _, ch := range chs {
		cases = append(cases, reflect.SelectCase{
			Dir:  reflect.SelectRecv,
			Chan: reflect.ValueOf(ch),
		})
	}

	for i, ch := range chs {
		cases = append(cases, reflect.SelectCase{
			Dir:  reflect.SelectSend,
			Chan: reflect.ValueOf(ch),
			Send: reflect.ValueOf(i),
		})
	}

	return cases
}
