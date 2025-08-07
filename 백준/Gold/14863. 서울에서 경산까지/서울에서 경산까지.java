import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[K + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int wk = Integer.parseInt(st.nextToken());
            long wp = Long.parseLong(st.nextToken());
            int bk = Integer.parseInt(st.nextToken());
            long bp = Long.parseLong(st.nextToken());
            for (int k = K; k >= 0; k--) {
                if (dp[k] < 0) continue;
                long p = dp[k];
                dp[k] = -1;
                if (k + wk <= K) dp[k + wk] = Math.max(dp[k + wk], p + wp);
                if (k + bk <= K) dp[k + bk] = Math.max(dp[k + bk], p + bp);
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsLong());
    }
}