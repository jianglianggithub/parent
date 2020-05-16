package clientServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(2333));
        final Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        //将 该通道注册到选择器中 选择触发回调函数 得事件绑定
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT,SelectionKey.OP_WRITE);

        while (true){
            // 阻塞  一直等到 有 事件触发时 返回 触发得事件数量
            selector.select();
            // 获得所有 注册到 选择其中得事件(已触发得事件)
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            final Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                final SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    final ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    final SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    //  注册 read事件   当 客户端向该 socket通道 写入数据时候 就会触发 该sokcet服务端得read事件
                    // 当注册 对应得通道+通道绑定得对应事件 后 会返回一个 selectkey 封装了 该通道 和 选择器 等等
                    // 和到时候 触发事件 返回得 是同一个对象  会放到容器中
                    accept.register(selector,SelectionKey.OP_READ);
                    //remove  在等待处理得事件set集合中删除 当前 已经处理完得事件
                    iterator.remove();
                }else if (selectionKey.isReadable()){
                    final SocketChannel channel = (SocketChannel) selectionKey.channel();
                    final ByteBuffer allocate = ByteBuffer.allocate(1024);
                    while (channel.read(allocate) != -1){
                        allocate.flip();
                        System.out.println(new String(allocate.array(),0,allocate.limit()));
                        allocate.clear();
                    }
                    iterator.remove();
                }else if (selectionKey.isWritable()){
                    final ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.put("我是你爹".getBytes());
                    allocate.flip();
                    ((SocketChannel)selectionKey.channel()).write(allocate);

                    allocate.clear();
                    iterator.remove();
                }
            }

        }

    }
}
