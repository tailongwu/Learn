package share_mem

import (
	"sync"
	"testing"
)

func TestCounter(t *testing.T) {
	var mut sync.Mutex

	counter := 0
	for i := 0; i < 5000; i++ {
		go func() {
			defer func() {
				mut.Unlock()
			}()
			mut.Lock()
			counter++
		}()
	}
	// time.Sleep(1 * time.Second)
	t.Logf("counter = %d", counter)
}

func TestCounterWaitGroup(t *testing.T) {
	var mut sync.Mutex
	var wg sync.WaitGroup
	counter := 0
	for i := 0; i < 5000; i++ {
		wg.Add(1)
		go func() {
			defer func() {
				wg.Done()
				mut.Unlock()
			}()
			mut.Lock()
			counter++
		}()
	}
	wg.Wait()
	// time.Sleep(1 * time.Second)
	t.Logf("counter = %d", counter)
}