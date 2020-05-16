package com.组合模式;

public class File implements AbstractDirectory {
   String name;

   public File(String name) {
       this.name=name;
   }
    @Override
    public void add(AbstractDirectory abstractDirectory) {

    }

    @Override
    public void delete(AbstractDirectory abstractDirectory) {

    }

    @Override
    public void print() {
        System.out.println(name);
    }


    @Override
    public String toString() {
        return "文件名 : "+name;
    }


    public static void main(String[] args) {
        Directory directory1 = new Directory("第一级文件夹");
        directory1.add(new File("文件1"));
        Directory directory2 = new Directory("文件夹1里面的文件夹");
        directory1.add(directory2);
        directory2.add(new Directory("文件夹2里面的文件夹"));
        directory1.print();
   }
}
