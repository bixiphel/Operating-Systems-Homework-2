public class Worker extends Thread {
    // Instance variables
    private Matrix matrix;       // Shared matrix
    private Matrix nextMatrix;   // Matrix to store updated values
    private final int startRow;        // First row this thread handles
    private final int endRow;          // Last row this thread handles
    private int iterations;            // Count of iterations this thread ran
    private double threshold;          // Sets the error threshold
    private long t1;
    private long t2;

    // Constructor without barrier (simple multithreading)
    public Worker(Matrix m1, Matrix m2, int startRow, int endRow, double threshold) {
        this.matrix = m1;
        this.nextMatrix = m2;
        this.startRow = startRow;
        this.endRow = endRow;
        this.iterations = 0;
        this.threshold = threshold;
        t1 = 0;
        t2 = 0;
    }

    @Override
    public void run() {
        // This is for the "getId()" method, which was deprecated at some point. This is a small project so it doesn't matter.
        @SuppressWarnings("deprecation")
        long threadID = getId();
        
        System.out.println("Hello from thread " + threadID + "!");
        
        // Calculates the error between the original matrix and the next iteration
        // Note that the average of a given matrix is the average of *every* cell in the matrix **INCLUDING** uninitialized variables.    
        double matrixError = matrix.totalAverage();
        double nextMatrixError = nextMatrix.totalAverage();
        double totalError = Math.abs(nextMatrixError - matrixError);
        
        t1 = System.nanoTime();
        
        while(totalError > threshold) {
            for(int r = startRow; r <= endRow; r++) {
                for(int c = 0; c < matrix.getSize(); c++) {
                    if(matrix.isFixed(r,c)) {
                        nextMatrix.setValue(r,c,matrix.getValue(r,c),true);
                    } else {
                        nextMatrix.setValue(r,c,matrix.computeNeighborAverage(r,c), false);
                    }
                } 
            }
            
            // Sets up for the next iteration
            matrixError = matrix.totalAverage();
            nextMatrixError = nextMatrix.totalAverage();
            totalError = Math.abs(nextMatrixError - matrixError);
            matrix = nextMatrix;
            nextMatrix = new Matrix(matrix.getSize());
            iterations++;
            t2 = System.nanoTime();            
        }
                
        // Prints out results
        System.out.printf("Total Grid Error: %f | Grid Average Temperature: %f | Iterations run on thread %d: %d%nExecution Time (ms): %f%n", totalError, nextMatrixError, threadID, iterations, (1.0*(t2-t1))/1000000);
    }
}
