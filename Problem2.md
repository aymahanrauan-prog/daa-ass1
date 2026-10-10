# Median of Two Sorted Arrays (`Problem2`)

This project contains a Java implementation comparing two approaches for finding the median of two sorted arrays (`A` and `B`): a brute-force merging approach (`getMedianBrute`) and an optimized binary search partitioning approach (`getMedianSmart`).

---

## 🛠️ Code Fix Notes

Before compiling and running the original code, make sure to fix the following syntax and formatting issues:

1. **In `getMedianBrute` (split line for index calculation):**
   ```java
   // Incorrect (original):
   return (merged[total / 2 
   1] + merged[total / 2]) / 2.0;

   // Corrected:
   return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
   ```

2. **In `getMedianSmart` (missing minus sign for partition B):**
   ```java
   // Incorrect (original):
   int partitionB = (m + n + 1) / 2 partitionA;

   // Corrected:
   int partitionB = (m + n + 1) / 2 - partitionA;
   ```

3. **In `main` method signature:**
   ```java
   // Incorrect (original):
   public static void main() {

   // Corrected:
   public static void main(String[] args) {
   ```

---

## 🧠 Approach & Algorithmic Design

### 1. Brute Force Merge (`getMedianBrute`)
* **Mechanism:** Creates a new merged array of size $M + N$, iterates through both arrays using a two-pointer technique to combine them in sorted order, and then extracts the median based on whether the total number of elements is odd or even.
* **Limitations:** Requires allocating extra memory for the merged array and takes linear time relative to the total size of both arrays.

### 2. Optimized Binary Search Partition (`getMedianSmart`)
* **Mechanism:** Ensures that `A` is the smaller array (swapping if necessary) and performs a binary search on the partition index of `A` (`partitionA`). It computes `partitionB` such that the left halves and right halves of both arrays are perfectly balanced.
* **Validation:** Checks if the elements on the left are smaller than or equal to the elements on the right (`maxLeftA <= minRightB` and `maxLeftB <= minRightA`). Once the correct partition is found, it calculates the median in $O(1)$ time.

---

## 📊 Complexity Analysis

| Approach | Method Name | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Merge / Brute Force** | `getMedianBrute` | $O(M + N)$ | $O(M + N)$ |
| **Binary Search Partition** | `getMedianSmart` | $O(\log(\min(M, N)))$ | $O(1)$ |

* **$M$ and $N$**: Lengths of arrays `A` and `B` respectively.
* **$O(\log(\min(M, N)))$**: The binary search is performed exclusively on the smaller array, making it extremely efficient even for massive datasets.

---

## 🚀 Running the Benchmark / Examples

The `main` method demonstrates the solution with two test cases:
* **Example 1:** `A1 = {1, 2, 3}`, `B1 = {3, 4, 5}` $\rightarrow$ Expected Median: `3.0`
* **Example 2:** `A2 = {1, 2}`, `B2 = {3, 4}` $\rightarrow$ Expected Median: `2.5`

### Compilation and Execution
To compile and run the program:

```bash
javac Problem2.java
java Problem2
```

### Expected Output
```text
Example 1 Brute: 3.0
Example 1 Smart: 3.0
Example 2 Brute: 2.5
Example 2 Smart: 2.5
```

---

## 💡 Conclusion
While `getMedianBrute` is straightforward to implement, `getMedianSmart` provides an optimal logarithmic time complexity and constant auxiliary space, making it the standard interview-level and production-ready solution for this problem.