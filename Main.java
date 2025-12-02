public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        Worker thread1 = new Worker();
        thread1.start();
    }
}