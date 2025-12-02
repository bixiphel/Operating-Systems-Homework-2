public class Matrix {
    // Instance variables
    private int size;
    private static double[][] matrix;
    
    /** 
    * Default constructor for a matrix object. Initializes the size to 1 with a value of 0.
    */
    public Matrix() {
        size = 1;
        matrix = new double[size][size];
    }
    
    /** 
    * 1-arg constructor for an integer (square) matrix object, which is a nxn matrix of integers.
    * @param size Sets the size of the matrix. 
    */
    public Matrix(int size) {
        this.size = size;
        matrix = new double[size][size];
    }
    
    /**
    * 1-arg constructor for initializing a matrix with values. 
    */
    public Matrix(String values) {
        // Parses the string of values
        String[] parts = values.split("-");
        double[] numbers = new double[parts.length];
        
        // Adds the separated values to the 'numbers' array
        for(int i = 0; i < parts.length; i++) {
            // Checks if the ith value is actually initalized or not. If it is not (i.e. is represented by the value x), then its value is initialized to the minimum integer value to avoid confusion with other values.
            if(parts[i].equals("x")) {
                numbers[i] = Double.NEGATIVE_INFINITY;
            } else {
                numbers[i] = Double.parseDouble(parts[i]);
            }
        }
        
        // Calculates the max size of a square matrix using the provided values. This always rounds down, so if the string of provided values isn't a perfect square, some values will be lost
        int maxSize = (int)Math.sqrt(numbers.length);
        
        size = maxSize;
        matrix = new double[size][size];
        
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
                System.out.printf("%11f%3s",matrix[i][j], "");
            }
            System.out.println();
        }
    }
}