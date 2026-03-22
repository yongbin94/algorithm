import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        long I = Long.parseLong(st.nextToken());
        dp = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) dp[0][i] = 1;

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        int l = L;
        for (int n = N; n >= 1; n--) {
            long cnt = dp[n - 1][l];
            if (I <= cnt) {
                sb.append('0');
            } else {
                sb.append('1');
                I -= cnt;
                l--;
            }
        }
        System.out.println(sb);
    }
}