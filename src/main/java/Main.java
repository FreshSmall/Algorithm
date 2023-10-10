import java.math.BigDecimal;
import java.text.DecimalFormat;

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

//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//
//        List<String> list2 = new ArrayList<>();
//        list2.add("b");
//        list2.add("a");
//        list2.add("c");
//        list2.add("d");
//
//        list2.retainAll(list);
//        System.out.println(list2);

        double d = 0.0019;
        System.out.println(formatDecimal1(d));
        System.out.println(formatDecimal2(d));
        System.out.println(formatDecimal3(d));
        System.out.println(formatDecimal4(d));

    }

    public static String formatDecimal1(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    public static String formatDecimal2(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
    public static String formatDecimal3(double d){
        return String.format("%.2f",d);
    }

    public static double formatDecimal4(double d){
        BigDecimal bd=new BigDecimal(d);
        double d1=bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return d1;
    }
}
