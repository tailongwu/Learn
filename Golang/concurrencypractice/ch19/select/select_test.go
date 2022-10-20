package _select

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

func doWork() chan string {
	ch := make(chan string)
	go func() {
		time.Sleep(time.Second * 1)
		ch <- "test"
	}()
	return ch
}

func TestSelect(t *testing.T) {
	select {
	case ret := <- doWork():
		t.Log(ret)
	case <- time.After(time.Second * 1):
		t.Error("timeout")
	}
}

func dataProducer(ch chan int, wg *sync.WaitGroup) {
	go func() {
		for i := 0; i < 10; i++ {
			ch <- i
		}
		close(ch)
		wg.Done()
	}()
}

func dataReceiver(ch chan int, wg *sync.WaitGroup) {
	go func() {
		for {
			data, ok := <- ch
			fmt.Printf("receiver1: %d\n", data)
			if !ok {
				break
			}
		}
		wg.Done()
	}()
}

func dataReceiver2(ch chan int, wg *sync.WaitGroup) {
	go func() {
		for {
			data, ok := <- ch
			fmt.Printf("receiver2: %d\n", data)
			if !ok {
				break
			}
		}
		wg.Done()
	}()
}

func TestCloseChannel(t *testing.T) {
	var wg sync.WaitGroup
	ch := make(chan int)
	wg.Add(1)
	dataProducer(ch, &wg)

	wg.Add(1)
	dataReceiver(ch, &wg)

	wg.Add(1)
	dataReceiver2(ch, &wg)
	wg.Wait()
}
