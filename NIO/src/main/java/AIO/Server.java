package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel open = AsynchronousServerSocketChannel.open();

        open.bind(new InetSocketAddress(2333));

        open.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                final ByteBuffer readBuff = ByteBuffer.allocate(1024);
                result.read(readBuff, readBuff, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer a, ByteBuffer attachment) {
                        attachment.flip();
                        System.out.println(new String(attachment.array(),0,attachment.limit()));
                        attachment.clear();

                        final ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.put("server huifu".getBytes());
                        allocate.flip();
                        result.write(allocate);


                        result.read(attachment,attachment,this);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });

            }

            @Override
            public void failed(Throwable exc, Void attachment) {

            }
        });

        while (true){
            Thread.sleep(1000);
        }
    }
}
