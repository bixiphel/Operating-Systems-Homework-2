import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {        
        // Creates initialized matrix using values provided in the assignment overview  
        Matrix m1 = new Matrix(5);
              
        for(int i = 1; i < 4; i++) {
            // Sets the cells with a '15' in it
            m1.setValue(i,0,15,true);
            
            // Sets the cells with a '30' in it
            m1.setValue(0,i,30,true);
            
            // Sets the cells with a '75' in it
            m1.setValue(4,i,75,true);
            
            // Sets the cells with a '72' in it
            m1.setValue(i,4,72,true);
        }
        
        // Creates empty matrix to use as the next step
        Matrix m2 = new Matrix(5);
        
        // Step 1: Single Thread Solution
        int count = 5;
        double threshold = 5.0;
        
        System.out.println("---------- SingleThread Test ----------");
        SingleThread thread1 = new SingleThread(m1, m2, count, threshold);
        thread1.run();
        
        // -----------------------------------------------------------------
        
        // Step 2: MultiThread Solution
        System.out.println("\n---------- MultiThread Test ----------");

        int numThreads = 4;

        MultiThread mt = new MultiThread(m1, m2, numThreads, threshold);
        mt.run();
        
        // -----------------------------------------------------------------
        
        // Step 3: Barrier solution
        System.out.println("---------- Barrier Test ----------");
        numThreads = 4;   // 4 threads like step 2

        // Create the barrier that waits for all threads
        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        // Create arrays for threads
        Thread[] threads = new Thread[numThreads];

        int rowsPerThread = m1.getSize() / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == numThreads - 1) ? m1.getSize() : startRow + rowsPerThread;

            threads[i] = new BarrierWorker(m1, m2, startRow, endRow, threshold, barrier);

            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
    }
}
