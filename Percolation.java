import javax.management.RuntimeErrorException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int numOpenSites;
    boolean[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n needs to be bigger than 0");

        grid = new boolean[n][n];
        numOpenSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        var n = grid.length;
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        var wasOpen = grid[col][row];

        if (!wasOpen)
            numOpenSites++;

        grid[col][row] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        var n = grid.length;
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        return grid[col][row];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        var n = grid.length;
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Required: 1 <= row <= n && 1 <= col <= n. Here row=" + row + " col=" + col);

        throw new RuntimeErrorException(null, "Not implemented yet");
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return true; // ######## fill in
    }

    // test client (optional)
    public static void main(String[] args) {
        if (args.length == 2) {
            var n = Integer.valueOf(args[0]);
            var t = Integer.valueOf(args[1]);
        }
    }
}