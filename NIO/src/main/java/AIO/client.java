package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class client {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final AsynchronousSocketChannel open = AsynchronousSocketChannel.open();
        open.connect(new InetSocketAddress("127.0.0.1",2333)).get();
        final Scanner scanner = new Scanner(System.in);

        final ByteBuffer readByteBuff = ByteBuffer.allocate(1024);
        open.read(readByteBuff, readByteBuff, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                    attachment.flip();
                    System.out.println(new String(attachment.array(),0,attachment.limit()));
                    attachment.clear();

                    open.read(attachment,attachment,this);

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (scanner.hasNext()){
            final String next = scanner.next();
            byteBuffer.put(next.getBytes());
            byteBuffer.flip();
            open.write(byteBuffer);

            byteBuffer.clear();
        }

    }
}
