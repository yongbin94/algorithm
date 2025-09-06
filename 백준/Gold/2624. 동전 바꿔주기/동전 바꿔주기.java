import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[T + 1];
        dp[0] = 1;
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            for (int t = T; t > 0; t--) {
                for (int n = 1; n <= N; n++) {
                    int v = t - n * p;
                    if (v < 0) break;
                    if (dp[v] == 0) continue;
                    dp[t] += dp[v];
                }
            }
        }
        System.out.println(dp[T]);
    }
}
