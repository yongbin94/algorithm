import java.util.*;

public class Main {
    static int N;
    static long answer;
    static long[] F;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        F = new long[N + 1];
        factorial(N);
        int R = sc.nextInt();
        int G = sc.nextInt();
        int B = sc.nextInt();
        recur(0, R, G, B, 1);
        System.out.println(answer);
    }

    private static void recur(int lev, int r, int g, int b, long cnt) {
        if (lev++ == N) {
            answer += cnt;
            return;
        }
        if (r >= lev) recur(lev, r - lev, g, b, cnt);
        if (g >= lev) recur(lev, r, g - lev, b, cnt);
        if (b >= lev) recur(lev, r, g, b - lev, cnt);

        if (lev % 2 == 0) {
            int half = lev / 2;
            long f = F[lev] / (F[half] * F[half]);

            if (r >= half && g >= half) recur(lev, r - half, g - half, b, cnt * f);
            if (r >= half && b >= half) recur(lev, r - half, g, b - half, cnt * f);
            if (g >= half && b >= half) recur(lev, r, g - half, b - half, cnt * f);
        }

        if (lev % 3 == 0) {
            int third = lev / 3;
            long f = F[lev] / (F[third] * F[third] * F[third]);

            if (r >= third && g >= third && b >= third) 
                recur(lev, r - third, g - third, b - third, cnt * f);
        }
    }

    private static void factorial(int n) {
        F[0] = 1;
        for (int i = 1; i <= n; i++) {
            F[i] = F[i - 1] * i;
        }
    }
}