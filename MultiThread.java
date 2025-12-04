public class MultiThread extends Thread {

    // Instance Variables
    private Matrix m1;
    private Matrix m2;
    private int numThreads;      // how many threads to run (1–4)
    private double threshold;
    private Worker[] workers;    // array of worker thread objects

    /**
     * Constructor for the multithreaded controller.
     * @param m1 The initial matrix of fixed values.
     * @param m2 A blank matrix of equal size.
     * @param numThreads Number of worker threads (1 to 4).
     * @param threshold Error threshold each thread is allowed.
     */
    public MultiThread(Matrix m1, Matrix m2, int numThreads, double threshold) {
        this.m1 = m1;
        this.m2 = m2;
        this.numThreads = numThreads;
        this.threshold = threshold;
        this.workers = new Worker[numThreads];
    }

    /**
     * Runs the multithreaded grid solver.
     */
    @Override
    public void run() {
        long t1 = System.nanoTime();

        int size = m1.getSize();
        int rowsPerThread = size / numThreads;
        int remainder = size % numThreads;

        int start = 0;

        // Create workers, assigning row ranges
        for (int i = 0; i < numThreads; i++) {
            int extra = (i < remainder) ? 1 : 0;  // distribute remainder rows
            int end = start + rowsPerThread + extra - 1;

            workers[i] = new Worker(m1, m2, start, end, threshold);

            start = end + 1;
        }

        // Start all workers
        for (Worker w : workers) {
            w.start();
        }

        // Join all workers (wait for completion)
        for (Worker w : workers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t2 = System.nanoTime();

        // Final results output
        System.out.printf("All %d threads completed. Total time: %.4f (ms)%n", numThreads, (1.0 * (t2 - t1)) / 1000000);
    }
}
