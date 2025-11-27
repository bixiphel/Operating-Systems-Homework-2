public class Matrix {
    // Instance variables
    private int size;
    private static int[][] matrix;
    
    // 0-arg constructor
    public Matrix() {
        size = 1;
        matrix = new int[size][size];
    }
    
    // 1-arg constructor
    public Matrix(int size) {
        this.size = size;
        matrix = new int[size][size];
    }
    
    // Print method
    public static void print() {
        System.out.println("Test!\n");
        
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            
            
            }
            System.out.println();
        }
    }
}