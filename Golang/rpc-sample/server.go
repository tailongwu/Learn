package main

import (
	"fmt"
	"net"
	"net/http"
	"net/rpc"
)

type Watcher int

func (w *Watcher) GetInfo(arg int, result *int) error {
	*result = 5 * arg
	return nil
}

func main() {
	watcher := new(Watcher)
	rpc.Register(watcher)
	rpc.HandleHTTP()

	l, err := net.Listen("tcp", ":1234")
	if err != nil {
		fmt.Println("Failed to listen, the port could be occurpied")
	}
	fmt.Println("Listening port 1234")
	http.Serve(l, nil)
}
