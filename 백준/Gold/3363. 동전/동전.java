import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int[] A = new int[13];
        boolean[] B = new boolean[13];
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            int[] a = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};
            char o = sc.next().charAt(0);
            int[] b = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};
            for (int j = 0; j < 4; j++) {
                B[a[j]] = true;
                B[b[j]] = true;
            }
            if (o == '<') {
                int[] tmp = a;
                a = b;
                b = tmp;
            }
            if (o != '=') {
                cnt++;
                for (int n : a) {
                    A[n]++;
                }
                for (int n : b) {
                    A[n]--;
                }
            } else {
                for (int n : a) {
                    A[n] += 10;
                }
                for (int n : b) {
                    A[n] += 10;
                }
            }
        }
        boolean possible = false;
        for (int n = 1; n <= 12; n++) {
            if (Math.abs(A[n]) == cnt) {
                possible = true;
            }
        }
        if (!possible) {
            System.out.println("impossible");
            return;
        }
        int res = 0;
        for (int n = 1; n <= 12; n++) {
            if (!B[n]) {
                System.out.println("indefinite");
                return;
            }
            if (Math.abs(A[n]) == cnt) {
                if (res != 0) {
                    System.out.println("indefinite");
                    return;
                }
                res = n;
            }
        }
        System.out.println(res + (A[res] > 0 ? "+" : "-"));
    }
}