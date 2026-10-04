import java.util.Arrays;
import java.util.Comparator;

public class Problem4 {

    public double minDistBrute(double[][] p) {
        if (p == null || p.length < 2) {
            return 0.0;
        }

        double minDistance = Double.POSITIVE_INFINITY;
        int n = p.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = distance(p[i], p[j]);
                if (dist < minDistance) {
                    minDistance = dist;
                }
            }
        }

        return minDistance;
    }

    public double minDistSmart(double[][] p) {
        if (p == null || p.length < 2) {
            return 0.0;
        }

        int n = p.length;
        double[][] pointsX = new double[n][2];
        double[][] pointsY = new double[n][2];

        for (int i = 0; i < n; i++) {
            pointsX[i] = p[i];
            pointsY[i] = p[i];
        }

        Arrays.sort(pointsX, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        Arrays.sort(pointsY, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                return Double.compare(a[1], b[1]);
            }
        });

        return closestRecursive(pointsX, pointsY, n);
    }

    private double closestRecursive(double[][] pointsX, double[][] pointsY, int n) {
        if (n <= 3) {
            return minDistBrute(pointsX);
        }

        int mid = n / 2;
        double[] midPoint = pointsX[mid];

        double[][] leftY = new double[mid][2];
        double[][] rightY = new double[n - mid][2];
        int li = 0, ri = 0;

        for (int i = 0; i < n; i++) {
            if ((pointsY[i][0] < midPoint[0] || (pointsY[i][0] == midPoint[0] && pointsY[i][1] <= midPoint[1])) && li < mid) {
                leftY[li++] = pointsY[i];
            } else {
                rightY[ri++] = pointsY[i];
            }
        }

        double[][] leftX = new double[mid][2];
        double[][] rightX = new double[n - mid][2];
        for (int i = 0; i < mid; i++) {
            leftX[i] = pointsX[i];
        }
        for (int i = mid; i < n; i++) {
            rightX[i - mid] = pointsX[i];
        }

        double dLeft = closestRecursive(leftX, leftY, mid);
        double dRight = closestRecursive(rightX, rightY, n - mid);
        double d = Math.min(dLeft, dRight);

        double[][] strip = new double[n][2];
        int stripSize = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(pointsY[i][0] - midPoint[0]) < d) {
                strip[stripSize++] = pointsY[i];
            }
        }

        return Math.min(d, stripClosest(strip, stripSize, d));
    }

    private double stripClosest(double[][] strip, int size, double d) {
        double min = d;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j][1] - strip[i][1]) < min; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }

        return min;
    }

    private double distance(double[] p1, double[] p2) {
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void main() {
        var solver = new Problem4();
        System.out.println(solver.minDistBrute(new double[][]{{0, 0}, {3, 4}, {5, 3}}));
        System.out.println(solver.minDistSmart(new double[][]{{0, 0}, {3, 4}, {5, 3}}));
    }
}
