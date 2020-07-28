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


    public static void main(String[] args) throws InterruptedException {

        final Demo d = new Demo();
        d.start();
        d.join();
        System.out.println("Hello World!");
    }
}
