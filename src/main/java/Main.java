public class Main {

    static class Demo extends Thread{

        @Override
        public void run() {
            System.out.println("调用线程");
        }
    }



    public static void main(String[] args) throws InterruptedException {

        final Demo d = new Demo();
        d.start();
        d.join();
        System.out.println("Hello World!");
    }
}