import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Parent {




    public void gg(){

    }



    class BBB{
        public void aaa(){
            gg();
        }
    }


    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/sss.av"), "rw");
       rw.seek(8);
        System.out.println(rw.readInt());
    }
}
