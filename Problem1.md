# Frequency Count Analysis (`Problem1`)

This repository contains a Java implementation comparing two approaches for counting the frequency of a specific key in a sorted array: a linear scan with early exit (`countFreqBrute`) and an optimized binary search approach (`countFreqSmart`).

---

## Code Fix Note

Before compiling the original code, make sure to fix the syntax/formatting issue in the `findFirst` and `findLast` methods where the right boundary was incorrectly split across lines:

```java
// Incorrect (original):
int left = 0, right = A.length 
1;

// Corrected:
int left = 0, right = A.length - 1;
```

---

## Algorithmic Complexity

| Approach | Method Name | Time Complexity (Worst) | Time Complexity (Best) | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **Brute Force (Linear)** | `countFreqBrute` | O(N) | O(1) | O(1) |
| **Smart (Binary Search)** | `countFreqSmart` | O(log N) | O(log N) | O(1) |

- **Linear Search (`countFreqBrute`):** Iterates through the sorted array sequentially. It leverages the sorted property to break early once elements exceed the key, but still requires linear scanning relative to the key's location and frequency.
- **Binary Search (`countFreqSmart`):** Utilizes two modified binary searches (`findFirst` and `findLast`) to locate the boundaries of the key range in O(log N) time, computing the frequency instantly via `last - first + 1`.

---

## Running the Benchmark

The `main` method sets up an array of N = 100,000 elements (filled such that `A[i] = i / 100`), searches for `key = 500`, and measures execution time in nanoseconds.

To compile and run:

```bash
javac Problem1.java
java Problem1
```

### Example Output
```text
--- Empirical Results ---
Brute result: 100 | Time: [Brute execution time in ns]
Smart result: 100 | Time: [Smart execution time in ns]
```

### Conclusion
For large datasets, `countFreqSmart` significantly outperforms the linear search method due to its O(log N) scaling behavior.