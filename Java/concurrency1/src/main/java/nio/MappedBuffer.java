package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedBuffer {
    // 文件直接在堆外内存修改，操作系统不需要拷贝一次
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(".\\nio-data.txt", "rw");
        FileChannel ch = file.getChannel();
        MappedByteBuffer map = ch.map(FileChannel.MapMode.READ_WRITE, 0, 4);
        map.put(0, (byte) 'H');
        map.put(3, (byte) 'H');
        file.close();
    }
}
