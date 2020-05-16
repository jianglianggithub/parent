package 二维数组;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class 二维数组 {

    //    \r 代表回到行首。\n 代表当前指针 下移 指针得位置不变  所以换行= \r\n
    public static void main(String[] args) throws IOException {

        int[][] array=new int[10][11];
        array[1][2]=1;
        array[2][3]=2;

        int[][] xssz = xstest(array);

        FileWriter stream = new FileWriter(new File("d:/map.data"));
        BufferedWriter bufferedWriter = new BufferedWriter(stream);
        for (int[] ints : xssz) {
            for (int inta : ints) {
                bufferedWriter.write(inta+"\t");
            }
            bufferedWriter.write("\r\n");
        }
        bufferedWriter.close();

        BufferedReader reader = new BufferedReader(new FileReader(new File("d:/map.data")));

        List<String> list = IOUtils.readLines(reader);
        System.out.println(list);
        for (String s : list) {
            String[] split = s.split("\t");
            for (String s1 : split) {
                System.out.print(s1);
            }
            System.out.println();
        }

        array= fy(xssz);
        System.out.println(array[1][2]);
        System.out.println(array[2][3]);
    }

    private static int[][] fy(int[][] xssz) {
        int lngth1=xssz[0][0];
        int length2=xssz[0][1];
        int[][] fy = new int[lngth1][length2];
        for (int i = 1; i < xssz.length; i++) {

                fy[    xssz[i][0]   ] [   xssz[i][1]      ]=xssz[i][2];

        }
        return fy;
    }

    private static int[][] xstest(int[][] array) {
        int[][] xssz=new int[1][3];
        xssz[0][0]=11;
        xssz[0][1]=11;
        xssz[0][2]=2;
        int length=1;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] != 0){
                    if (length >= xssz.length) {
                        xssz = kr(xssz);
                    }
                    xssz[length][0]=i;
                    xssz[length][1]=j;
                    xssz[length][2]=array[i][j];
                    length++;
                }

            }

        }
        System.out.println(xssz[1][0]);
        System.out.println(xssz[1][1]);
        System.out.println(xssz[1][2]);
        System.out.println();
        System.out.println(xssz[2][0]);
        System.out.println(xssz[2][1]);
        System.out.println(xssz[2][2]);
        System.out.println();
        System.out.println(xssz.length);
        return xssz;
    }

    public static int[][] kr(int[][] a){
        int[][] b=new int[a.length+1][a[0].length];
        System.arraycopy(a,0,b,0,a.length);
        return b;
    }


}
