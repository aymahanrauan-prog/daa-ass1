# Maximum Subarray Sum (`Problem3`)

This project contains a Java implementation and empirical analysis comparing two approaches for finding the maximum contiguous subarray sum within an integer array: a nested-loop brute force approach (`maxSumBrute`) and a divide-and-conquer optimization approach (`maxSumSmart`).

---

## 🛠️ Code Fix Notes

Before compiling and running the original code, make sure to fix the following syntax issues:

1. **In `maxSumSmart` (missing minus sign for the right boundary):**
   ```java
   // Incorrect (original):
   return maxSubArrayHelper(A, 0, A.length 
   1);

   // Corrected:
   return maxSubArrayHelper(A, 0, A.length - 1);
   ```

2. **In `main` method signature (missing `public` and `String[] args`):**
   ```java
   // Incorrect (original):
   static void main() {

   // Corrected:
   public static void main(String[] args) {
   ```

---

## 🧠 Approach & Algorithmic Design

### 1. Brute Force (`maxSumBrute`)
* **Mechanism:** Iterates through all possible starting indices `i` and ending indices `j` of subarrays, dynamically accumulating the sum for each subarray.
* **Limitations:** Results in a quadratic time complexity because it exhaustively checks every combination of subarrays.

### 2. Divide and Conquer (`maxSumSmart`)
* **Mechanism:** Recursively splits the array into two halves (left and right), similar to Merge Sort:
    1. Finds the maximum subarray sum in the left half (`leftMax`).
    2. Finds the maximum subarray sum in the right half (`rightMax`).
    3. Finds the maximum subarray sum that crosses the midpoint (`crossMax`) using `maxCrossingSum`.
* **Result:** Returns the maximum of these three values (`Math.max(Math.max(leftMax, rightMax), crossMax)`).

---

## 📊 Complexity Analysis

| Approach | Method Name | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Brute Force** | `maxSumBrute` | O(N^2) | O(1) |
| **Divide and Conquer** | `maxSumSmart` | O(N log N) | O(log N) |

* **N**: Number of elements in the array.
* **O(log N) Space**: The divide-and-conquer approach uses recursive function calls, consuming stack space proportional to the height of the recursion tree.

---

## 🚀 Running the Examples

The `main` method tests both algorithms using the sample array: `{-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4}`.

### Compilation and Execution
To compile and run the program:

```bash
javac Problem3.java
java Problem3
```

### Expected Output
Both methods will output the maximum contiguous subarray sum for the given test case.

---

## 💡 Conclusion
While `maxSumBrute` is simple and uses constant space, `maxSumSmart` leverages a divide-and-conquer strategy to reduce time complexity to O(N log N). *(Note: While this implementation uses Divide and Conquer, this problem can also be solved in O(N) linear time using Kadane's Algorithm).*