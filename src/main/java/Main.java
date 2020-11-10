import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        list.add("d");

        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("a");
        list2.add("c");
        list2.add("d");

        List<String> list3 = list2.stream().collect(Collectors.toList());

        list3.retainAll(list);
        list.removeAll(list3);
        list2.removeAll(list3);

        System.out.println(list.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());

    }
}
