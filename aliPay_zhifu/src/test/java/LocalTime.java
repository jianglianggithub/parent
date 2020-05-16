import com.Testa;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.rocketmq.store.util.LibC;
import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class LocalTime {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        System.out.println(dayOfMonth);

    }


    @Test
    public void test(){
        ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
        Object key = hashMap.computeIfAbsent("key", k -> new Object());
        Object key1 = hashMap.computeIfAbsent("key", k -> new Object());

        System.out.println(key==key1);

    }

    @Test
    public void test1(){
        int[] ints={1,2,1,2,3,6};

        for (int i = 0; i < ints.length; i++) {
            for (int j = i+1; j < ints.length; j++) {

                if (ints[i] < ints[j]) {
                    int temp=ints[i];
                    ints[i]= ints[j];
                    ints[j] = temp;

                }
            }
        }

        for (int i = 0; i <ints.length
                ; i++) {
            System.out.print(ints[i]);
        }
    }

    @Test
    public void test2(){
        int[] ints={1,2,1,2,3,6};

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i -1; j++) {
                if (ints[j] > ints[j+1] ) {
                    int temp=ints[j];
                    ints[j]= ints[j+1];
                    ints[j+1] = temp;
                }
            }
        }

        for (int i = 0; i <ints.length
                ; i++) {
            System.out.print(ints[i]);
        }
    }

    @Test
    public void test3() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("d1");

        for (int i = 0; i < 2; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue("value"+j);
            }
        }


        workbook.write(new FileOutputStream(new File("d:/aa.xls")));
        workbook.close();
    }

    @Test
    public void test5() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("d:/aa.xls")));
        HSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);

        // 在 poi中 row 的lastRowNum 是 index 最大 <=

        // 在 列 cell 中 lastCell 是 个数。所以是 <
    }

    @Test
    public void test6() throws IOException, InterruptedException {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/aaa"), "rw");
        MappedByteBuffer mmap = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);

//        for (int i = 0; i < rw.getChannel().size(); i+= 4096) {
//            mmap.put(i,(byte)0);
//
//            if (i  == rw.getChannel().size() - 4096) {
//                System.out.println("1");
//                mmap.force();//当刷盘后 pageCache 的 缓存 就会被 转移到 swap分区了。
//                LockSupport.park();
//            }
//        }
        long size = rw.getChannel().size();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicInteger integer= new AtomicInteger(0);
        long[] ints=new long[4];
        for (int i = 0; i < 4; i++) {
            ints[i]=(i+1) * size / 4;
        }

        for (int i = 0; i < 4; i++) {
            executorService.execute(()->{
                long end = ints[integer.getAndAdd(1)];
                int start = (int) (end - ((1024 * 1024 * 1024) / 4));
                System.out.println(start+" "+ end);
                for ( ; start < end; start++) {

                    if (start == size-1) {
                        System.out.println("ok");
                    }
                    mmap.put(start,(byte)22);
                }
                System.out.println(Thread.currentThread().getName()+"结束");
            });

        }

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
        System.out.println("aa");
        mmap.force();
    }

    @Test
    public void test7() throws IOException, InterruptedException {
        ServiceLoader<Testa> load = ServiceLoader.load(Testa.class);
        Testa next = load.iterator().next();
        System.out.println(next);
    }
    @Test
    public void test8() throws IOException, InterruptedException {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/aaa"), "rw");
        MappedByteBuffer mmap = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        for (int j = 0; j < rw.getChannel().size(); j+= 4096) {
            mmap.getInt(j);
        }
        LockSupport.park();
    }

    @Test
    public void test9() throws IOException, InterruptedException {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/aaa"), "rw");
        MappedByteBuffer mmap = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);

        for (int i = 0; i < rw.getChannel().size(); i+= 4096) {
            mmap.put(i,(byte)0);
        }

        final long address = ((DirectBuffer) mmap).address();
        Pointer pointer = new Pointer(address);
        {
            int ret = LibC.INSTANCE.mlock(pointer, new NativeLong(rw.getChannel().size()));
        }

        LockSupport.parkUntil(3000);
    }

    @Test
    public void test11() throws IOException, InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.exit(-1);
    }
}
