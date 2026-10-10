# Large Integer Multiplication (`Problem5`)

This project contains a Java implementation and empirical analysis comparing two approaches for multiplying large integers represented as strings: a schoolbook multiplication approach (`multBrute`) and an optimized divide-and-conquer Karatsuba algorithm approach (`multSmart`).

---

## 🛠️ Code Fix Notes

Before compiling and running the original code, make sure to fix the following syntax issues and typographical errors:

1. **In `main` method signature (missing `public` and `String[] args`):**
   ```java
   // Incorrect (original):
   static void main() {

   // Corrected:
   public static void main(String[] args) {
   ```

2. **In `karatsuba` method (missing multiplication operator for shift size):**
   ```java
   // Incorrect (original):
   String term1 = shiftLeft(p1, 2 half);

   // Corrected:
   String term1 = shiftLeft(p1, 2 * half);
   ```

3. **In `subtractStrings` method (missing minus sign for the borrow variable):**
   ```java
   // Incorrect (original):
   int digit1 = num1.charAt(i--) - '0' borrow;

   // Corrected:
   int digit1 = num1.charAt(i--) - '0' - borrow;
   ```

---

## 🧠 Approach & Algorithmic Design

### 1. Schoolbook Multiplication (`multBrute`)
* **Mechanism:** Mimics manual column multiplication taught in school. It processes strings from right to left, multiplying each digit of string `B` by each digit of string `A`, accumulating the carries into an integer array of size $m + n$.
* **Limitations:** Quadratic time complexity makes it slow when multiplying extremely large numbers (e.g., hundreds of thousands of digits).

### 2. Karatsuba Algorithm (`multSmart`)
* **Mechanism:** A fast, recursive divide-and-conquer algorithm that reduces the multiplication of two $n$-digit numbers down to three multiplications of smaller numbers (each of size roughly $n/2$) instead of four:
    1. Splits numbers into high and low halves ($a_1, a_0$ and $b_1, b_0$).
    2. Recursively computes three products: $p_1 = a_1 \times b_1$, $p_2 = a_0 \times b_0$, and $p_3 = (a_1 + a_0) \times (b_1 + b_0)$.
    3. Combines them using linear additions, subtractions, and power-of-10 shifts.

---

## 📊 Complexity Analysis

| Approach | Method Name | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Schoolbook Multiplication** | `multBrute` | O(N^2) | O(N) |
| **Karatsuba Algorithm** | `multSmart` | O(N^1.585) | O(N) |

* **$N$**: Length of the input strings (number of digits).
* **$O(N^{\log_2 3}) \approx O(N^{1.585})$**: Karatsuba significantly outperforms schoolbook multiplication for large inputs by replacing one multiplication with additions and subtractions.

---

## 🚀 Running the Examples

The `main` method multiplies two large numeric strings (`12345678987654321` and `98765432123456789`) and compares the result against Java's built-in `BigInteger`.

### Compilation and Execution
To compile and run the program:

```bash
javac Problem5.java
java Problem5
```

### Expected Output
```text
Brute: [Multiplication result]
Smart: [Multiplication result]
BigInteger Check: [Multiplication result]
```

---

## 💡 Conclusion
While `multBrute` is straightforward and sufficient for standard numbers, `multSmart` (Karatsuba's algorithm) provides an advanced divide-and-conquer optimization that saves substantial processing time for massive multi-digit numbers.