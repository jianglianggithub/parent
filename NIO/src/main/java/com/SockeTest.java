package com;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class SockeTest {

     @Test
    public void test1() throws Exception{
         final FileChannel fileChannel = FileChannel.open(new File("d:\\aa.doc").toPath(), StandardOpenOption.READ);
         //final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
         final FileChannel fileChannel1 = FileChannel.open(new File("d:\\b.doc").toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//         while (fileChannel.read(byteBuffer) != -1){
//             byteBuffer.flip();
//             fileChannel1.write(byteBuffer);
//             byteBuffer.clear();
//         }

       //  fileChannel.transferTo(0,fileChannel.size(),fileChannel1);
         // 把  目标文件 读取到 读到 直接缓存中   该缓存 只负责读
         final MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
         //创建 读-写得 直接缓存
         final MappedByteBuffer map1 = fileChannel1.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
         //接下来之所以 不用 往 通道里面写了 是因为 你给 输入得通道 输入得通道 分别 都绑定了 一个直接缓存
         // 那么你直接往  写入得通道中 写入 那么就是在 直接写入文件

         // 之前之所以需要写 是因为 输入 输出 通道 是没有绑定 缓存得 所以需要 指定 哪个缓存字节数组 来写入

         // 如果 把输出融到绑定了 自己得专用 缓存 那么你只要写到缓存中 自动就写入了文件


     }



     @Test
    public void test2() throws Exception {
         SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2333));
         final FileChannel fileChannel = FileChannel.open(new File("d:\\aa.doc").toPath(), StandardOpenOption.READ);
         //fileChannel.transferTo(0,fileChannel.size(),socketChannel);
         final ByteBuffer allocate = ByteBuffer.allocate(1024);

         while (fileChannel.read(allocate) != -1){
             allocate.flip();
             socketChannel.write(allocate);
             allocate.clear();
         }

        socketChannel.shutdownOutput();
         // 由于 在 上面 向服务端 发送了数据 没有告知 服务端 已经发送完成 在客户端线程走到这个地方得时候 由于通道中
         // 是没有数据可以读取得 那么就会一直处于阻塞状态 等待服务 端 响应数据  所以成了一个死循环


         while (socketChannel.read(allocate) != -1){
             System.out.println(allocate.position());
             allocate.flip();
             System.out.println(allocate.array().length);
             System.out.println(new String(allocate.array(),0,allocate.limit()));
             allocate.clear();
         }

         fileChannel.close();
         socketChannel.close();

     }

    @Test
    public void test3() throws Exception {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(2333));
        //  accpet 是 等待一个链接 只要有 客户端 请求这个端口号 那么 就 成了一个 socket长连接 那么阻塞状态打断
        // 继续走下面
        final SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("====");
        final ByteBuffer allocate = ByteBuffer.allocate(1024);
        final FileChannel open = FileChannel.open(new File("d:\\b.doc").toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //  这儿得read 当读取不到数据得时候 就会一直 处于 阻塞状态
        //  如果 客户端不发出一个 shwodowm命令 那么 服务端 就一直处于阻塞状态  觉得 客户端得数据并没有发送完
        // 只有 客户端发出 指令 说发完了之后 这儿 才会 = -1 继续下面得执行
        while (socketChannel.read(allocate) != -1){
            allocate.flip();
            open.write(allocate);
            allocate.clear();
        }

        final byte[] bytes = "你吗死了".getBytes();

        final ByteBuffer put = ByteBuffer.allocate(1024).put(bytes);
        System.out.println(put.limit());
        //  这儿 必须 flip 确定一下limit 得 有效作用范围  有可能 jvm在进行网络传输得时候 为了节省带宽资源
        // 把你 有需要得内容进行传输 由于 你put了之后 limit  没有刷新 还不是你真正 占用得字节长度  所以存在一个 压缩 问题。
        // 刷新一下 limit 确定占用得 字节数 然后在发送 这样就不会乱码了 说明 java会优化一下把你真正占用得字节 发送过去
        put.flip();
        socketChannel.write(put);
        socketChannel.close();
        serverSocketChannel.close();

        Selector.open().select();
    }


    @Test
    public void aaa(){
        final Scanner scanner = new Scanner(System.in);
        final boolean b = scanner.hasNext();
        if (b){
            System.out.println(scanner.next());
        }
    }




    @Test
    public void client() throws Exception{
        final SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2333));
        open.configureBlocking(false);
        final Scanner scanner = new Scanner(System.in);
        //当 hashNext得时候 会阻塞 一直等到 你输入之后 才返回结果  也就是有返回结果 一定输入了。。。=死循环
        while (scanner.hasNext()){
            final String next = scanner.next();
            final ByteBuffer allocate = ByteBuffer.allocate(1024);
            open.write(allocate.put(next.getBytes()));
        }

    }


    @Test
    public void server() throws IOException {
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












    @Test
    public void copyFile() throws Exception{
        final FileChannel open = FileChannel.open(new File("d:\\aa.doc").toPath(), StandardOpenOption.READ);
        final FileChannel open1 = FileChannel.open(new File("d:\\bb.doc").toPath(), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        //
        final MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
        //  position 定义得是 起初得 索引 从哪儿开始写   size= 内部得字节数组得 长度
        final MappedByteBuffer map1 = open1.map(FileChannel.MapMode.READ_WRITE, 0, open.size());
        System.out.println(map1.position());

        map1.put(map);
        System.out.println(map1.position()+ " "+map1.limit());
        open.close();
        open1.close();

    }
}
