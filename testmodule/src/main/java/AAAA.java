import java.io.File;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class AAAA {

    public static void main(String[] args) {
        test(new File("D:\\java\\code\\dubbo-master"));
    }


    public static void test(File file) {
        File[] files = file.listFiles();

        for (File file1 : files) {
            if (file1.isDirectory()) {
                test(file1);
            }else {
                if (file1.getName().contains(".iml") || file1.getName().contains(".ipr") || file1.getName().contains(".iws")) {
                    System.out.println(file1.getName());
                    file1.delete();
                }
            }
        }

    }
}
