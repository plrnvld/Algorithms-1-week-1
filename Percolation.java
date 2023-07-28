import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private int n;
    private int topNode;
    private int bottomNode;
    private int numOpenSites;
    private boolean[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0)
            throw new IllegalArgumentException("n needs to be bigger than 0");

        uf = new WeightedQuickUnionUF(n * n + 2);
        topNode = n * n;
        bottomNode = n * n + 1;

        grid = new boolean[n][n];
        numOpenSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        if (!isOpen(row, col))
            numOpenSites++;

        var currNum = getNum(row, col);

        if (row == 1) // top row
            uf.union(topNode, currNum);
        if (row == n) // bottom row
            uf.union(bottomNode, currNum);

        if (row > 1 && isOpen(row - 1, col)) // left
            uf.union(getNum(row - 1, col), currNum);
        if (row < n && isOpen(row + 1, col)) // right       
            uf.union(getNum(row + 1, col), currNum);

        if (col > 1 && isOpen(row, col - 1)) // up       
            uf.union(getNum(row, col - 1), currNum);
        if (col < n && isOpen(row, col + 1)) // down       
            uf.union(getNum(row, col + 1), currNum);        

        grid[row - 1][col - 1] = true;
    }

    private int getNum(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        return uf.find(topNode) == uf.find(getNum(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topNode) == uf.find(bottomNode);
    }

    // test client (optional)
    public static void main(String[] args) {
        
    }
}