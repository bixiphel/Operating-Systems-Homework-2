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

        // Recreate matrices so they start with the same values
        Matrix m1_multi = new Matrix(5);
        Matrix m2_multi = new Matrix(5);

        for(int i = 1; i < 4; i++) {
            m1_multi.setValue(i,0,15,true);
            m1_multi.setValue(0,i,30,true);
            m1_multi.setValue(4,i,75,true);
            m1_multi.setValue(i,4,72,true);
        }

        int numThreads = 4;

        MultiThread mt = new MultiThread(m1_multi, m2_multi, numThreads, threshold);
        mt.run();
    }
}
