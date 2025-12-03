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
        
        // Step 1: Single Thread Solution
        // Creates empty matrix to use as the next step
        Matrix m2 = new Matrix(5);
        
        // Initializes variables to compare results
        double sum = 0;
        int count = 2;
        double time = 0;
        
        for(int i = 0; i < count; i++) {
            // Creates a thread
            Worker thread1 = new Worker(m1, m2, 0, 4, 5.0);
                    
            // Times how long the thread execution takes
            long t1 = System.nanoTime();
            thread1.run();
            long t2 = System.nanoTime();
            
            time = (1.0 * (t2 - t1))/1000000;
            
            System.out.printf("Execution time (ms): %f%n%n", time);
            
            sum += time;
        }
                
        System.out.printf("Number of threads run: %d | Total Execution Time (ms): %f | Average execution time per thread (ms): %f", count, sum, sum/count); 
    }
}