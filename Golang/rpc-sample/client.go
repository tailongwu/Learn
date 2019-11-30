package main

import (
	"fmt"
	"net/rpc"
)

func test() {
	client, err := rpc.DialHTTP("tcp", "127.0.0.1:1234")
	if err != nil {
		fmt.Println("Failed to connect server", err)
	}
	var reply int
	err = client.Call("Watcher.GetInfo", 1, &reply)
	if err != nil {
		fmt.Println("Failed to call server", err)
	}
	fmt.Println("The result from remote server is: ", reply)
}
