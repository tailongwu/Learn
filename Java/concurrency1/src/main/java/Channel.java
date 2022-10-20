import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Channel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(8);
        while (channel.read(buf) != -1) {
            System.out.println("xxxx");
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char)buf.get());
            }
            buf.clear();
        }
        channel.close();

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
