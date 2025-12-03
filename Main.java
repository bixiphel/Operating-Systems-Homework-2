public class Main {
    public static void main(String[] args) {  
        Matrix m1 = new Matrix(5);
        Matrix m2 = new Matrix(5);
              
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
        System.out.println("m1:");
        m1.print();
        System.out.println("\n\nm2:");
        m2.print();
        
        // Creates a thread
        Worker thread1 = new Worker(m1, m2, 0, 4, 2);
        thread1.run();
              
    }
}