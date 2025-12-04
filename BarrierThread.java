import java.util.concurrent.CyclicBarrier;

public class BarrierThread extends Thread {
    // Instance Variables
    private Matrix m1;
    private Matrix m2;
    private int numThreads;
    private double threshold;
    private BarrierWorker[] workers;
    private CyclicBarrier barrier;

    /**
    * 4-arg constructor for a BarrierThread object
    * @param m1 The matrix of fixed values
    * @param m2 A blank matrix of equal size
    * @param numThreads The number of threads to execute on m1
    * @param threshold The max error allotted to a given thread
    */
    public BarrierThread(Matrix m1, Matrix m2, int numThreads, double threshold) {
        this.m1 = m1;
        this.m2 = m2;
        this.numThreads = numThreads;
        this.threshold = threshold;

        // Barrier: waits for all threads + main thread
        this.barrier = new CyclicBarrier(numThreads);
        this.workers = new BarrierWorker[numThreads];
    }

    @Override
    public void run() {
        long t1 = System.nanoTime();

        int size = m1.getSize();
        int rowsPerThread = size / numThreads;
        int remainder = size % numThreads;

        int start = 0;

        // Create worker threads
        for (int i = 0; i < numThreads; i++) {
            int extra = (i < remainder) ? 1 : 0;
            int end = start + rowsPerThread + extra - 1;

            workers[i] = new BarrierWorker(
                m1,
                m2,
                start,
                end,
                threshold,
                barrier
            );

            start = end + 1;
        }

        // Start workers
        for (BarrierWorker w : workers)
            w.run();

        // Wait for all workers to finish
        for (BarrierWorker w : workers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t2 = System.nanoTime();
        System.out.printf("BarrierThread (%d threads) completed in %.4f ms%n", numThreads, (1.0 * (t2 - t1)) / 1000000);
    }
}
