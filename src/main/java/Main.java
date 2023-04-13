import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Demo extends Thread{

        @Override
        public void run() {
            System.out.println("调用线程");
        }
    }

    public void testMethod(){
        System.out.println("测试main地址");
    }

    public void testMethod1(){
        System.out.println("测试main地址");
    }
    public void testMethod2(){
        System.out.println("测试main地址");
    }
    public void testMethod3(){
        System.out.println("测试main地址");
    }

    public void testMethod4(){
        System.out.println("测试main地址");
    }

    public void testMethod5(){
        System.out.println("测试main地址");
    }
    public void testMethod6(){
        System.out.println("测试main地址");
    }

    public void testMethod7(){
        System.out.println("测试main地址");
    }

    public void testMethod8(){
        System.out.println("测试main地址");
    }

    public static void main(String[] args) throws InterruptedException {

//        final Demo d = new Demo();
//        d.start();
//        d.join();
//        System.out.println("Hello World!");

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("a");
        list2.add("c");
        list2.add("d");

        list2.retainAll(list);
        System.out.println(list2);

    }
}
