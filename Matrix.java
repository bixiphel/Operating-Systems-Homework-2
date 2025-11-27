public class Matrix {
    // Instance variables
    private int size;
    private static int[][] matrix;
    
    /** 
    * Default constructor for a matrix object. Initializes the size to 1 with a value of 0.
    */
    public Matrix() {
        size = 1;
        matrix = new int[size][size];
    }
    
    /** 
    * 1-arg constructor for an integer (square) matrix object, which is a nxn matrix of integers.
    * @param size Sets the size of the matrix. 
    */
    public Matrix(int size) {
        this.size = size;
        matrix = new int[size][size];
    }
    
    /**
    * 1-arg constructor for initializing a matrix with values. 
    */
    public Matrix(String values) {
        // Parses the string of values
        String[] parts = values.split("-");
        int[] numbers = new int[parts.length];
        
        // Adds the separated values to the 'numbers' array
        for(int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        
        // Calculates the max size of a square matrix using the provided values. This always rounds down, so if the string of provided values isn't a perfect square, some values will be lost
        int maxSize = (int)Math.sqrt(numbers.length);
        
        size = maxSize;
        matrix = new int[size][size];
        
        // This is the index of the 'values' array
        int k = 0;
        
        // Adds the kth element to the matrix object left-to-right, top-to-bottom.
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = numbers[k++];
            }
        }
        
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