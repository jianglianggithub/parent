package bio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class client {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 2333));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("nmsl".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.shutdownOutput();

        while (socketChannel.read(byteBuffer) >0){
            System.out.println(byteBuffer.limit()+"   posison="+byteBuffer.position());
            System.out.println(new java.lang.String(byteBuffer.array(), 0, byteBuffer.limit()));
            byteBuffer.clear();
        }
    }
}
