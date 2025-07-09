import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long[] dp = new long[N + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            dp[1] = 12;
            dp[2] = 34;
            dp[3] = 56;
            dp[4] = 1256;
            dp[5] = 3456;
            for (int n = 6; n <= N; n++) {
                for (int i = 1; i < n; i++) {
                    dp[n] = Math.min(dp[n], concat(dp[i], dp[n - i]));
                }
                dp[n] = Math.min(dp[n], concat(val(1, dp[n / 2]), dp[n % 2]));
                dp[n] = Math.min(dp[n], concat(dp[n % 2], val(1, dp[n / 2])));
                dp[n] = Math.min(dp[n], concat(val(3, dp[n / 3]), dp[n % 3]));
                dp[n] = Math.min(dp[n], concat(dp[n % 3], val(3, dp[n / 3])));
                dp[n] = Math.min(dp[n], concat(val(5, dp[n / 5]), dp[n % 5]));
                dp[n] = Math.min(dp[n], concat(dp[n % 5], val(5, dp[n / 5])));
            }
            sb.append(print(dp[N])).append("\n");
        }
        System.out.println(sb);
    }

    private static long concat(long a, long b) {
        long t = 1;
        while (t < b) t *= 10;
        return a * t + b;
    }

    private static long val(int v, long n) {
        long t = 1;
        while (t < n) t *= 10;
        return v * t * 10 + n * 10 + v + 1;
    }

    private static String print(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            switch ((int) (n % 10)) {
                case 1: sb.append("("); break;
                case 2: sb.append(")"); break;
                case 3: sb.append("{"); break;
                case 4: sb.append("}"); break;
                case 5: sb.append("["); break;
                case 6: sb.append("]"); break;
            }
            n /= 10;
        }
        return sb.reverse().toString();
    }
}