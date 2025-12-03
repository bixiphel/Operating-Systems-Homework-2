public class Matrix {
    // Instance variables
    private final int size;
    private double[] matrix; // For future optimizations, the matrix itself is represented as a 1d array with a size of n*n
    private boolean[] fixed; // Similar to the matrix, but contains flags for cells that are fixed values
    
    /** 
    * Default constructor for a matrix object. Initializes the size to 1 with a value of 0.
    */
    public Matrix() {
        size = 1;
        matrix = new double[size * size];
        fixed = new boolean[size*size];
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
        fixed = new boolean[size*size];

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
        size = (int) Math.floor(Math.sqrt(parts.length)); 
     
        // Instantiates the matrix and its flags arrays
        matrix = new double[size * size];
        fixed = new boolean[size*size];
        
        
        // Fills the matrix with as many of the provided values as it can
        for(int i = 0; i < size*size; i++) {
            // Blank values are filled with negative infinity by default to distinguish them from other values 
            if(parts[i].equals("x")) {
                matrix[i] = Double.NEGATIVE_INFINITY;
            } else {
                matrix[i] = Double.parseDouble(parts[i]);
                fixed[i] = true;
            }
        }        
    }
    
    /**
    * Print method, which prints the values as a square grid.
    */
    public void print() {        
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
    public int getSize() {
        return size;
    }
    
    /**
    * Returns a specified value in the matrix.
    * @param r The row to search (0-indexed)
    * @param c The column to search (0-indexed)
    * @return A double representation of the value at (r,c)
    */
    public double getValue(int r, int c) {
        return matrix[r*size + c];
    }
    
    /**
    * Setter for a specific value in a matrix object
    * @param r The row position (0-indexed)
    * @param c The column position (0-indexed)
    * @param v The new value 
    * @param isFixed A flag that determines if the value should be fixed (i.e. is not updated) or not
    */
    public void setValue(int r, int c, double v, boolean isFixed) {
        if(r < 0 || r >= size || c < 0 || c >= size) {
            throw new IllegalArgumentException("Index (" + r + "," + c + ") is out of bounds.");
        } else {
            matrix[r*size + c] = v;
            fixed[r*size + c] = isFixed;
        }
    }
    
    /**
    * Returns if a given cell is fixed or not
    * @param r The row position (0-indexed)
    * @param c The column position (0-indexed
    * @return True if the cell at (r,c) is fixed, false otherwise
    */
    public boolean isFixed(int r, int c) {
        if(r < 0 || r >= size || c < 0 || c >= size) {
            throw new IllegalArgumentException("Index (" + r + "," + c + ") is out of bounds.");
        } else {
            return fixed[r*size + c];
        }
    }  
    
    /**
    * Calculates the average based on the neighbors of a given cell
    * @param r The row position (0-indexed)
    * @param c The column position (0-indexed
    * @return The average temperature for the cell at (r,c) based on its neighbors 
    */
    public double computeNeighborAverage(int r, int c) {
        double sum = 0.0; // Used to store the running sum
        int count = 0; // Used to calculate the average if the specified cell doesn't have 4 neighbors (i.e. a corner)
        
        // First checks if the specified cell is fixed or not
        if(!fixed[r*size + c]) {
            // up
            if(r > 0) {
                sum += matrix[(r-1)*size + c];
                count++;
            }
                        
            // down
            if(r < size - 1) {
                sum += matrix[(r+1)*size + c];
                count++;
            }
            
            // left
            if(c > 0) {
                sum += matrix[r*size + (c - 1)];
                count++;
            }
            
            //right
            if(c < size - 1) {
                sum += matrix[r*size + (c+1)];
                count++;
            }   
        }
        return sum / count;
    }   
}