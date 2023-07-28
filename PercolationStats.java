import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

//     // perform independent trials on an n-by-n grid
//     public PercolationStats(int n, int trials)

//     // sample mean of percolation threshold
//     public double mean()

//     // sample standard deviation of percolation threshold
//     public double stddev()

//     // low endpoint of 95% confidence interval
//     public double confidenceLo()

//     // high endpoint of 95% confidence interval
//     public double confidenceHi()

//    // test client (see below)
    public static void main(String[] args) {
        if (args.length == 2) {
            var n = Integer.valueOf(args[0]);
            var t = Integer.valueOf(args[1]);
            
            var randomMax = n + 1;

            for (int i = 0; i < t; i++) {
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

                System.out.println("Percolation after " + perc.numOpenSites + " rounds.");
            }
        }
        else {
            System.out.println("Please give n and T integers as arguments.");
        }
    }
}