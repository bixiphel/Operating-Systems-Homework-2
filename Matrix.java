public class Matrix {
    // Instance variables
    private static int size;
    private static double[] matrix; // For future optimizations, the matrix itself is represented as a 1d array with a size of n*n
    
    /** 
    * Default constructor for a matrix object. Initializes the size to 1 with a value of 0.
    */
    public Matrix() {
        size = 1;
        matrix = new double[size * size];
    }
    
    /** 
    * 1-arg constructor for an integer (square) matrix object, which is a nxn matrix of integers.
    * @param size Sets the size of the matrix. 
    */
    public Matrix(int size) {
    
        // Catches negative sizes
        if(size <= 0) {
            throw new IllegalArgumentException("Matrix size must be positive");
        }    
        
        this.size = size;
        matrix = new double[size * size];
    }
    
    /**
    * 1-arg constructor for initializing a matrix with values. 
    */
    public Matrix(String values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("String cannot be empty");
        }
    
        // Splits string on instances of "-"
        String[] parts = values.split("-");
        
        // Calculates the largest value of n where a nxn matrix can be created with the provided array of values
        this.size = (int) Math.floor(Math.sqrt(parts.length)); 
     
        // Fills the matrix with as many of the provided values as it can
        matrix = new double[size * size];
        for(int i = 0; i < size*size; i++) {
            // Blank values are filled with negative infinity by default to distinguish them from other values 
            if(parts[i].equals("x")) {
                matrix[i] = Double.NEGATIVE_INFINITY;
            } else {
                matrix[i] = Double.parseDouble(parts[i]);
            }
        }        
    }
    
    /**
    * Print method, which prints the values as a square grid.
    */
    public static void print() {        
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.printf("%11f ", matrix[i * size + j]);
            }
            System.out.println();
        }
    }
    
    /**
    * Returns the size of the matrix.
    */
    public double getSize() {
        return size;
    }
    
    /**
    * Returns a specified value in the matrix.
    * @param r The row to search (0-indexed)
    * @param c The column to search (0-indexed)
    */
    public double getValue(int r, int c) {
        return matrix[r*size + c];
    }
    
    /**
    * Setter for a specific value in a matrix object
    * @param r The row position (0-indexed)
    * @param c The column position (0-indexed)
    * @param v The new value 
    */
    public void setValue(int r, int c, double v) {
        if(r < 0 || r >= size || c < 0 || c >= size) {
            throw new IllegalArgumentException("Index (" + r + "," + c + ") is out of bounds.");
        } else {
            matrix[r*size + c] = v;
        }
    }
    
}