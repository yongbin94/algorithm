import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 2];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dp[n] = Math.max(dp[n], dp[n - 1]);
            if (n + t - 1 > N)
                continue;
            dp[n + t] = Math.max(dp[n + t], dp[n] + p);
        }
        System.out.println(Math.max(dp[N], dp[N + 1]));
    }
}
