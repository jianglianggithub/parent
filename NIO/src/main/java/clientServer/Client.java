package clientServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        final SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2333));
        open.configureBlocking(false);
        final Scanner scanner = new Scanner(System.in);
        final ByteBuffer allocate = ByteBuffer.allocate(1024);
        //当 hashNext得时候 会阻塞 一直等到 你输入之后 才返回结果  也就是有返回结果 一定输入了。。。=死循环
        while (scanner.hasNext()){
            final String next = scanner.next();
            allocate.put(next.getBytes());
            allocate.flip();
            open.write(allocate);
            allocate.clear();

            while (open.read(allocate) >0){
                allocate.flip();
                System.out.println(new String(allocate.array(),0,allocate.limit()));
                allocate.clear();
            }
        }
    }
}
