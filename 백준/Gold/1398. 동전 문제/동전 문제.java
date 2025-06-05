import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getDp();
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            long M = Long.parseLong(br.readLine());
            long cnt = 0;
            while (M >= 100) {
                long A = 1;
                boolean isEven = true;
                while (A <= M) {
                    A *= 10;
                    isEven = !isEven;
                }
                long pow = A / (isEven ? 100 : 10);
                int d = (int) (M / pow);
                cnt += dp[d];
                M -= (long) d * pow;
            }
            sb.append(cnt + dp[(int) M]).append("\n");
        }
        System.out.println(sb);
    }

    private static void getDp() {
        dp = new int[100];
        Arrays.fill(dp, 100_000_000);
        dp[0] = 0;
        for (int n = 1; n < 100; n++) {
            dp[n] = Math.min(dp[n], dp[n - 1] + 1);
            if (n >= 10) dp[n] = Math.min(dp[n], dp[n - 10] + 1);
            if (n >= 25) dp[n] = Math.min(dp[n], dp[n - 25] + 1);
        }
    }
}