public class Problem2 {
    public double getMedianBrute(int[] A, int[] B) {
        int m = (A == null) ? 0 : A.length;
        int n = (B == null) ? 0 : B.length;
        int total = m + n;

        int[] merged = new int[total];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (A[i] <= B[j]) {
                merged[k++] = A[i++];
            } else {
                merged[k++] = B[j++];
            }
        }

        while (i < m) {
            merged[k++] = A[i++];
        }

        while (j < n) {
            merged[k++] = B[j++];
        }
        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        }
    }

    public double getMedianSmart(int[] A, int[] B) {
        if (A == null) A = new int[0];
        if (B == null) B = new int[0];
        if (A.length > B.length) {
            return getMedianSmart(B, A);
        }
        int m = A.length, n = B.length;
        int low = 0, high = m;

        while (low <= high) {
            int partitionA = low + (high - low) / 2;
            int partitionB = (m + n + 1) / 2 - partitionA;

            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : A[partitionA - 1];
            int minRightA = (partitionA == m) ? Integer.MAX_VALUE : A[partitionA];

            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : B[partitionB - 1];
            int minRightB = (partitionB == n) ? Integer.MAX_VALUE : B[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftA, maxLeftB);
                } else {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                }
            } else if (maxLeftA > minRightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }

        throw new IllegalArgumentException("Входные массивы не отсортированы.");
    }


    public static void main() {
        Problem2 solver = new Problem2();

        int[] A1 = {1, 2, 3};
        int[] B1 = {3, 4, 5};
        System.out.println("Example 1 Brute: " + solver.getMedianBrute(A1, B1)); // Expected: 3.0
        System.out.println("Example 1 Smart: " + solver.getMedianSmart(A1, B1)); // Expected: 3.0

        int[] A2 = {1, 2};
        int[] B2 = {3, 4};
        System.out.println("Example 2 Brute: " + solver.getMedianBrute(A2, B2)); // Expected: 2.5
        System.out.println("Example 2 Smart: " + solver.getMedianSmart(A2, B2));// Expected: 2.5
    }
}