package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class NIOFileChannel {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {

        }
    }

    private static void transformFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(".\\b.jpg");
        FileChannel srcCH = fileInputStream.getChannel();
        FileChannel destCH = fileOutputStream.getChannel();
        destCH.transferFrom(srcCH, 0, srcCH.size());
        srcCH.close();
        destCH.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\niofiledata.txt");
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\miofiledata2.txt");
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(2);
        while (true) {
            int length = inputChannel.read(bf);
            if (length == -1) {
                break;
            }
            bf.flip();
            outputChannel.write(bf);
            bf.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\niofiledata.txt");
        FileChannel ch = fileInputStream.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(1024);
        ch.read(bf);
        bf.flip();

        byte[] bytes = new byte[bf.limit()];
        bf.get(bytes, 0, bf.limit());
        System.out.println(new String(bytes));
        fileInputStream.close();
    }

    private static void writeFile() throws IOException {
        String str = "Hello world";
        FileOutputStream fileOutputStream = new FileOutputStream(".\\niofiledata.txt");
        FileChannel ch = fileOutputStream.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(1024);
        bf.put(str.getBytes());
        bf.flip();

        ch.write(bf);

        fileOutputStream.close();
    }
}
