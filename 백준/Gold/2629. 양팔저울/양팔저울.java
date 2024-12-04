import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] dp = new boolean[N][40001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0][0] = true;
        dp[0][Integer.parseInt(st.nextToken())] = true;
        for (int n = 1; n < N; n++) {
            int v = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 40001; i++) {
                if (dp[n - 1][i]) {
                    dp[n][i] = dp[n - 1][i];
                    if (i + v < 40001) dp[n][i + v] = true;
                    if (i >= v) dp[n][i - v] = true;
                    if (v >= i) dp[n][v - i] = true;
                }
            }
        }
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0)
            sb.append(dp[N - 1][Integer.parseInt(st.nextToken())] ? "Y " : "N ");
        System.out.println(sb);
    }
}