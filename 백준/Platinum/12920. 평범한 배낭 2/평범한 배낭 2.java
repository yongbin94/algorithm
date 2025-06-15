import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1];
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            for (int k = 1; K > 0; k <<= 1) {
                int cnt = Math.min(k, K);
                int w = V * cnt;
                int v = C * cnt;
                K -= cnt;
                for (int i = M; i >= w; i--) {
                    dp[i] = Math.max(dp[i], dp[i - w] + v);
                }
            }
        }
        System.out.println(dp[M]);
    }
}