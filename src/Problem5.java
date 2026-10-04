import java.math.BigInteger;

public class Problem5 {

    public String multBrute(String A, String B) {
        if (A == null || B == null || A.isEmpty() || B.isEmpty()) {
            return "0";
        }

        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        int m = A.length();
        int n = B.length();
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int digitA = A.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int digitB = B.charAt(j) - '0';
                int sum = digitA * digitB + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < result.length && result[idx] == 0) {
            idx++;
        }

        while (idx < result.length) {
            sb.append(result[idx++]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String multSmart(String A, String B) {
        if (A == null || B == null || A.isEmpty() || B.isEmpty()) {
            return "0";
        }

        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        return karatsuba(A, B);
    }

    private String karatsuba(String A, String B) {
        if (A.length() < 10 || B.length() < 10) {
            return multBrute(A, B);
        }

        int n = Math.max(A.length(), B.length());
        A = padLeft(A, n);
        B = padLeft(B, n);

        int half = n / 2;

        String a1 = A.substring(0, n - half);
        String a0 = A.substring(n - half);
        String b1 = B.substring(0, n - half);
        String b0 = B.substring(n - half);

        String p1 = karatsuba(a1, b1);
        String p2 = karatsuba(a0, b0);

        String sumA = addStrings(a1, a0);
        String sumB = addStrings(b1, b0);
        String p3 = karatsuba(sumA, sumB);

        String middle = subtractStrings(p3, addStrings(p1, p2));

        String term1 = shiftLeft(p1, 2 * half);
        String term2 = shiftLeft(middle, half);

        String result = addStrings(addStrings(term1, term2), p2);
        return removeLeadingZeros(result);
    }

    private String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }

    private String subtractStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int borrow = 0;

        while (i >= 0) {
            int digit1 = num1.charAt(i--) - '0' - borrow;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;

            if (digit1 < digit2) {
                digit1 += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            sb.append(digit1 - digit2);
        }

        return removeLeadingZeros(sb.reverse().toString());
    }

    private String shiftLeft(String num, int zeros) {
        if (num.equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < zeros; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    private String padLeft(String str, int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() < length) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

    private String removeLeadingZeros(String str) {
        int idx = 0;
        while (idx < str.length() - 1 && str.charAt(idx) == '0') {
            idx++;
        }
        return str.substring(idx);
    }

    static void main() {
        var solver = new Problem5();
        String A = "12345678987654321";
        String B = "98765432123456789";

        System.out.println("Brute: " + solver.multBrute(A, B));
        System.out.println("Smart: " + solver.multSmart(A, B));

        BigInteger a = new BigInteger(A);
        BigInteger b = new BigInteger(B);
        System.out.println("BigInteger Check: " + a.multiply(b));
    }
}