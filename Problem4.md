# Closest Pair of Points (`Problem4`)

This project contains a Java implementation and empirical analysis comparing two approaches for finding the minimum distance between any pair of 2D points in a given set: a nested-loop brute force approach (`minDistBrute`) and an optimized divide-and-conquer geometric approach (`minDistSmart`).

---

## 🛠️ Code Fix Notes

Before compiling and running the original code, make sure to fix the `main` method signature by adding `public` and `String[] args` so that Java can execute it as an entry point:

```java
// Incorrect (original):
static void main() {

// Corrected:
public static void main(String[] args) {
}
```

---

## 🧠 Approach & Algorithmic Design

### 1. Brute Force (`minDistBrute`)
* **Mechanism:** Iterates through every possible pair of points using nested loops, calculates the Euclidean distance between them using `distance()`, and tracks the minimum distance found.
* **Limitations:** Quadratic time complexity makes it impractical for large datasets of points.

### 2. Divide and Conquer (`minDistSmart`)
* **Mechanism:** Emulates the classic geometric divide-and-conquer algorithm:
    1. **Preprocessing:** Sorts the points independently by their $X$ coordinates (`pointsX`) and $Y$ coordinates (`pointsY`).
    2. **Division:** Recursively splits the point set into left and right halves.
    3. **Conquest:** Computes the minimum distance in the left half (`dLeft`) and right half (`dRight`), taking their minimum as $d = \min(dLeft, dRight)$.
    4. **Combine (Strip Check):** Collects points that lie within a vertical strip of width $2d$ around the dividing line (`strip`) and checks cross-pairs within a bounded window to see if a shorter distance exists.

---

## 📊 Complexity Analysis

| Approach | Method Name | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Brute Force** | `minDistBrute` | $O(N^2)$ | $O(1)$ |
| **Divide and Conquer** | `minDistSmart` | $O(N \log N)$ | $O(N)$ |

* **$N$**: Number of points in the input array.
* **$O(N \log N)$**: The divide-and-conquer approach drastically reduces time complexity compared to the brute force method by avoiding unnecessary pairwise distance calculations.

---

## 🚀 Running the Examples

The `main` method tests both algorithms using a sample set of 2D points: `{{0, 0}, {3, 4}, {5, 3}}`.

### Compilation and Execution
To compile and run the program:

```bash
javac Problem4.java
java Problem4
```

### Expected Output
Both methods will compute and print the minimum Euclidean distance between the closest pair of points in the dataset.

---

## 💡 Conclusion
While `minDistBrute` is simple and requires no extra memory, `minDistSmart` scales exceptionally well for massive coordinate datasets, achieving an optimal $O(N \log N)$ time complexity through spatial divide-and-conquer techniques.