import java.io.*;
import java.util.*;

public class Main {
    static int N, A, L;
    static int[] dp;
    static int[][] prev;
    static boolean[][] damaged;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        dp = new int[L + 1];
        prev = new int[N + 1][L + 1];
        damaged = new boolean[N + 1][L + 1];
        Arrays.fill(dp, -1);
        Arrays.fill(prev[0], -1);
        dp[L] = A;
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int l = 1; l <= L; l++) {
                if (dp[l] == -1) continue;
                if (y == 0) {
                    prev[n][l] = l;
                    damaged[n][l] = true;
                    continue;
                }
                if (x >= 0 && y >= 0) {
                    if (l > y && dp[l - y] < dp[l]) {
                        dp[l - y] = dp[l];
                        prev[n][l - y] = l;
                        damaged[n][l - y] = true;
                    }
                    if (dp[l] >= x) {
                        dp[l] -= x;
                        prev[n][l] = l;
                    } else {
                        dp[l] = -1;
                    }
                } else if (x == -1) {
                    if (l > y && dp[l - y] < dp[l]) {
                        dp[l - y] = dp[l];
                        prev[n][l - y] = l;
                        damaged[n][l - y] = true;
                    }
                    dp[l] = -1;
                } else {
                    dp[l] = Math.max(0, dp[l] - x);
                    prev[n][l] = l;
                }
            }
        }
        print();
        System.out.println();
    }

    private static void print() {
        for (int i = 1; i <= L; i++) {
            if (dp[i] < 0) continue;
            StringBuilder sb = new StringBuilder();
            int n = N, l = i;
            while (prev[n][l] != -1) {
                sb.append(damaged[n][l] ? "L" : "A");
                l = prev[n--][l];
            }
            System.out.println("YES");
            System.out.println(sb.reverse());
            return;
        }
        System.out.println("NO");
    }
}