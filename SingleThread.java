public class SingleThread extends Thread {
    // Instance Variables
    private Matrix m1;
    private Matrix m2;
    private int count;
    private double threshold;
    
    /**
    * Constructor for a single thread object.
    * @param m1 The matrix of fixed values 
    * @param m2 A blank matrix of equal size
    * @param count The number of trials to run
    * @param threshold The threshold on the error each thread is allowed
    */
    public SingleThread(Matrix m1, Matrix m2, int count, double threshold) {
        this.m1 = m1;
        this.m2 = m2;
        this.count = count;
        this.threshold = threshold;
    }
    
    /**
    * Overridden method to run the thread.
    */
    @Override
    public void run() {
        // Starts a clock       
        long actualT1 = System.nanoTime();
        
        for(int i = 0; i < count; i++) {
            // Creates a thread
            Worker thread1 = new Worker(m1, m2, 0, m1.getSize() - 1, threshold);
                    
            // Runs the thread
            thread1.run();
        }
        
        // Stops the clock
        long actualT2 = System.nanoTime(); 
        
        // Calculates the total time spent running every thread
        double totalTime = (1.0 *(actualT2 - actualT1))/1000000;
        
        // Prints out the total execution time. 
        System.out.printf("Actual total run time: %f | Average *Total* Time per thread (ms): %f%n", totalTime, totalTime/count);
    
    
    }
}