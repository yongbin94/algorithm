import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int C = getArabNum(A) + getArabNum(B);
        System.out.println(C);
        System.out.println(getRomaNum(C));
    }

    private static int getArabNum(String str) {
        int i = 0;
        int num = 0;
        int L = str.length();
        while (i < L && str.charAt(i) == 'M') {
            num += 1000;
            i++;
        }
        if (i < L && str.charAt(i) == 'D') {
            num += 500;
            i++;
        }
        while (i < L && str.charAt(i) == 'C') {
            if (i + 1 < L && str.charAt(i + 1) == 'M') {
                num += 900;
                i += 2;
            } else if (i + 1 < L && str.charAt(i + 1) == 'D') {
                num += 400;
                i += 2;
            } else {
                num += 100;
                i++;
            }
        }
        if (i < L && str.charAt(i) == 'L') {
            num += 50;
            i++;
        }
        while (i < L && str.charAt(i) == 'X') {
            if (i + 1 < L && str.charAt(i + 1) == 'C') {
                num += 90;
                i += 2;
            } else if (i + 1 < L && str.charAt(i + 1) == 'L') {
                num += 40;
                i += 2;
            } else {
                num += 10;
                i++;
            }
        }
        if (i < L && str.charAt(i) == 'V') {
            num += 5;
            i++;
        }
        while (i < L && str.charAt(i) == 'I') {
            if (i + 1 < L && str.charAt(i + 1) == 'X') {
                num += 9;
                i += 2;
            } else if (i + 1 < L && str.charAt(i + 1) == 'V') {
                num += 4;
                i += 2;
            } else {
                num += 1;
                i++;
            }
        }
        return num;
    }

    private static String getRomaNum(int num) {
        StringBuilder sb = new StringBuilder();
        int M = num / 1000;
        int C = (num % 1000) / 100;
        int X = (num % 100) / 10;
        int I = num % 10;

        while (M-- > 0)
            sb.append("M");
        if (C == 9) {
            C = 0;
            sb.append("CM");
        }
        if (C >= 5) {
            C -= 5;
            sb.append("D");
        }
        if (C == 4) {
            C = 0;
            sb.append("CD");
        }
        while (C-- > 0)
            sb.append("C");


        if (X == 9) {
            X = 0;
            sb.append("XC");
        }
        if (X >= 5) {
            X -= 5;
            sb.append("L");
        }
        if (X == 4) {
            X = 0;
            sb.append("XL");
        }
        while (X-- > 0)
            sb.append("X");


        if (I == 9) {
            I = 0;
            sb.append("IX");
        }
        if (I >= 5) {
            I -= 5;
            sb.append("V");
        }
        if (I == 4) {
            I = 0;
            sb.append("IV");
        }
        while (I-- > 0)
            sb.append("I");

        return sb.toString();
    }
}