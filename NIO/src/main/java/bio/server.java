package bio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class server {
    public static void main(String[] args) throws Exception{
        final ServerSocketChannel bind = ServerSocketChannel.open().bind(new InetSocketAddress(2333));
        final SocketChannel accept = bind.accept();
        final ByteBuffer allocate = ByteBuffer.allocate(1024);

        while (accept.read(allocate) >0){
            allocate.flip();
            System.out.println(new java.lang.String(allocate.array(),0,allocate.limit()));
            allocate.clear();
        }
        accept.shutdownInput();

        allocate.put("lnms".getBytes());
        allocate.flip();

        accept.write(allocate);
    }
}
