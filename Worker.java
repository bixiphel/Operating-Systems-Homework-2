public class Worker extends Thread {
    @Override
    public void run() {
        // This is for the "getId()" method, which was deprecated at some point. This is a small project so it doesn't matter.
        @SuppressWarnings("deprecation")
        long threadID = getId();
        
        System.out.println("Hello from thread " + threadID + "!");
    }
}
