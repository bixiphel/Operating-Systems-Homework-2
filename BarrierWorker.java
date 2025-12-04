import java.util.concurrent.CyclicBarrier;

public class BarrierWorker extends Thread {
    // Instance variables
    private Matrix matrix;
    private Matrix nextMatrix;
    private int startRow;
    private int endRow;
    private double threshold;
    private CyclicBarrier barrier;
    private int iterations;

    /**
    * 6-arg constructor for a BarrierWorker object.
    * @param m1 The matrix of fixed values.
    * @param m2 A blank matrix of equal size
    * @param startRow The row to start on.
    * @param endRow The row to end on.
    * @param threshold The max allotted error for a given thread
    * @param barrier A CyclicBarrier object
    */
    public BarrierWorker(Matrix m1, Matrix m2,int startRow, int endRow, double threshold, CyclicBarrier barrier) {
        this.matrix = m1;
        this.nextMatrix = m2;
        this.startRow = startRow;
        this.endRow = endRow;
        this.threshold = threshold;
        this.barrier = barrier;
        this.iterations = 0;
    }

    @Override
    public void run() {
        try {
        
            // This is for the "getId()" method, which was deprecated at some point. This is a small project so it doesn't matter.
            @SuppressWarnings("deprecation")
            long threadID = getId();
            
            double matrixError = matrix.totalAverage();
            double nextMatrixError = nextMatrix.totalAverage();
            double totalError = Math.abs(nextMatrixError - matrixError);
            
            long t1 = System.nanoTime();
            
            while (totalError >= threshold) {

                // Compute next values for assigned rows
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < matrix.getSize(); col++) {
                        if (!matrix.isFixed(row, col)) {
                            double avg = matrix.computeNeighborAverage(row, col);
                            nextMatrix.setValue(row, col, avg, false);
                        }
                    }
                }

                // Every thread must wait here
                barrier.await();

                // Compute new error (only one thread must swap matrices)
                double oldAvg = matrix.totalAverage();
                double newAvg = nextMatrix.totalAverage();
                totalError = Math.abs(newAvg - oldAvg);

                // Swap references only after ALL have finished
                Matrix temp = matrix;
                matrix = nextMatrix;
                nextMatrix = temp;
                iterations++;

                // Wait again before starting next iteration
                barrier.await();
            }
            
            long t2 = System.nanoTime();
            
            // Prints out results
        System.out.printf("%nThread %d Finished.%nTotal Grid Error: %f | Grid Average Temperature: %f | Iterations run on thread %d: %d%nExecution Time (ms): %f%n%n", threadID, totalError, nextMatrixError, threadID, iterations, (1.0*(t2-t1))/1000000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
