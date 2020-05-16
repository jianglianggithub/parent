package 单向链表;

public class Wraaper {

    private No head;


    public void add(No no){
        if (head ==null) {
            head=no;
            return;
        }
        No temp=head;
        while (temp.next != null ){
            temp=temp.next;
        }
        temp.next=no;
    }

    public void remove(No no) {

        if (head == null)
            throw new RuntimeException();
        No pre=null;
        No cur=head;
        while (cur != no && cur.next != null) {
            pre=cur;
            cur=cur.next;
        }

        if (cur != no) {
            throw new RuntimeException();
        }

        if (pre == null) {
            head=cur.next;
        }else {
            pre.next=cur.next;
        }
    }

    public void show(){
        No cur=head;
        while (cur != null) {
            System.out.println(cur);
            cur=cur.next;

        }



    }

    public static void main(String[] args) {
        No no = new No();
        no.name="0";

        No no1 = new No();
        no1.name="1";

        No no2 = new No();
        no2.name="2";

        No no3 = new No();
        no3.name="3";

        No no4 = new No();
        no4.name="4";

        Wraaper wraaper = new Wraaper();
        wraaper.add(no);
        wraaper.add(no1);
        wraaper.add(no2);
        wraaper.add(no3);
        wraaper.add(no4);

        wraaper.show();


        wraaper.remove(no3);

        wraaper.show();
    }


}
