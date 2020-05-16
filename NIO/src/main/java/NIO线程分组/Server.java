package NIO线程分组;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {


    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(2333));
        serverSocketChannel.configureBlocking(false);
        Selector open = Selector.open();
        SelectionKey register = serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);

        System.out.println(open.selectNow());

        for (SelectionKey selectedKey : open.selectedKeys()) {

        }

    }
}

class AccpetThread extends Thread{

    public Selector selector;

    public Thread[] threads;
    public AccpetThread() throws IOException {
        selector=Selector.open();
    }

    public void active(ServerSocketChannel serverSocketChannel) throws ClosedChannelException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    @Override
    public void run() {
        while (true){
            try {
                selector.select();
                final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    final SelectionKey next = iterator.next();
                    if (next.isAcceptable()){
                        final ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                        final SocketChannel accept = channel.accept();

                        iterator.remove();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
