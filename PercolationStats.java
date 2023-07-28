import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private double[] results;
    private int trials;
    
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;

        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();
        
        var randomMax = n + 1;

        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            var perc = new Percolation(n);

            boolean percolates = false;

            while (!percolates) {
                var row = StdRandom.uniformInt(1, randomMax);
                var col = StdRandom.uniformInt(1, randomMax);               

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    percolates = perc.percolates();
                }
            }

            // System.out.println("Percolation after " + perc.numOpenSites + " rounds.");
            results[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev()/Math.sqrt((double) trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev()/Math.sqrt((double) trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length == 2) {
            var n = Integer.parseInt(args[0]);
            var t = Integer.parseInt(args[1]);

            if (n < 1 || t < 1)
                throw new IllegalArgumentException("n and t need to be 1 or higher");
            
            var stats = new PercolationStats(n, t);
            System.out.println("mean = " + stats.mean());
            System.out.println("stddev = " + stats.stddev());
            System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        }
        else {
            System.out.println("Please give n and T integers as arguments.");
        }
    }
}