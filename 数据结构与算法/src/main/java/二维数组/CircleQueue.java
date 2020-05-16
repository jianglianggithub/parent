package 二维数组;

/**
 *  如果必须是2个指针的话。 那么 必须预留一个 index 。 不然只能在 初始化的时候就  向里面增加一个。 当 putindex == readindex 就代表满了
 *
 *
 *   不然 只能  预留一个 为什么 因为不预留的话。只有当相等的时候才满。但是一来就是 读写都-0 那么岂不是 不成立 了
 *
 *      putindex > readindex     在已知 putindex 一定小于 maxindex    得情况下 减去maxindex 其实跟 % 取模一个样
 *     putindex + 1  - maxindex=  readindex
 *
 *          putindex < readindex
 *     putindex +1 = readindex
 *
 *     上面2个表达式 中和起来 就是这个 公式。 所以没必要纠结这个公式
 *     (putindex +1 ) % maxindex == readindex
 */
public class CircleQueue {
    //初始化数组的长度，用来判断
    private int maxSize;
    //起始索引
    private int front;
    //结束索引
    private int rear;
    //初始数组
    private int[] arr;

    public static void main(String[] args) {
        CircleQueue aa = new CircleQueue(5);
        aa.add(1);
        aa.add(1);
        aa.add(1);
        aa.add(1);

    }
    //初始化参数
    public CircleQueue (int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isEmpty () {
        return rear == front;
    }

    public boolean isFull () {
        return (rear + 1) % maxSize == front;
    }

    public void add (int n) {
        if(isFull()){
            System.out.println("queue is full.");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后后移
        rear = (rear + 1) % maxSize;
    }

    public int get () {
        if(isEmpty()){
            throw new RuntimeException();
        }
        int result = arr[front];
        front = (front + 1) % maxSize;
        return result;
    }

    //显示队列所有数据
    public void show () {
        if(isEmpty()){
            System.out.println("empty");
            return;
        }
        //假设maxSize=5,fron=2,rear=0
        //这确实是对的
        for(int i = front; i <front + size();i++){
            System.out.printf("arr[%d]=%d/t",i%maxSize,arr[i%maxSize]);
        }
    }

    public int size () {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     *
     *      putindex  > readindex
     *      putindex - readindex     多加一个maxxsize 然后取模 还是自己
     *
     *      putindex  < readindex
     *      putindex + (maxindex - readindex )    前者整体 得数肯定小于 maxSize 取模还是本身得值
     *
     *      这2个表达式 好像确实转换成 上面 没毛病。。
     */
}