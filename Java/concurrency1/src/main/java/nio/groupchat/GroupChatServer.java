package nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " Online");
                        }

                        if (key.isReadable()) {
                            readData(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("Waiting for connect...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
    
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            int count = channel.read(bf);
            if (count > 0) {
                String msg = new String(bf.array()).trim();
                System.out.println("From client: " + msg);

                sendDataToOtherClients(msg, channel);
            }
            
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " Leave");
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    
    private void sendDataToOtherClients(String msg, SocketChannel self) {
        System.out.println("Forward msg...");
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self) {
                try {
                    SocketChannel sc = (SocketChannel) channel;
                    sc.write(ByteBuffer.wrap(msg.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
