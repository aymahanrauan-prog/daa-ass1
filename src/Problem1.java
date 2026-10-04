public class Problem1 {

    public int countFreqBrute(int key, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == key) {
                count++;
            } else if (A[i] > key) {
                break;
            }
        }
        return count;
    }

    private int findFirst(int key, int[] A) {
        int left = 0, right = A.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == key) {
                result = mid;
                right = mid - 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private int findLast(int key, int[] A) {
        int left = 0, right = A.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == key) {
                result = mid;
                left = mid + 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public int countFreqSmart(int key, int[] A) {
        if (A == null || A.length == 0) return 0;

        int first = findFirst(key, A);
        if (first == -1) {
            return 0;
        }
        int last = findLast(key, A);

        return last - first + 1;
    }

    public static void main(String[] args) {
        Problem1 p = new Problem1();

        int n = 100000;
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = i / 100;
        }
        int key = 500;

        p.countFreqBrute(key, A);
        p.countFreqSmart(key, A);

        long startBrute = System.nanoTime();
        int resBrute = p.countFreqBrute(key, A);
        long endBrute = System.nanoTime();

        long startSmart = System.nanoTime();
        int resSmart = p.countFreqSmart(key, A);
        long endSmart = System.nanoTime();

        System.out.println("--- Empirical Results ---");
        System.out.println("Brute result: " + resBrute + " | Time: " + (endBrute - startBrute) + " ns");
        System.out.println("Smart result: " + resSmart + " | Time: " + (endSmart - startSmart) + " ns");
    }
}