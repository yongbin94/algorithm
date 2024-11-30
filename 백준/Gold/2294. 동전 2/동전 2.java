import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, K;
    static int[] A, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for(int n = 0; n < N; n++)
            A[n] = Integer.parseInt(br.readLine());
        Arrays.sort(A);
        for(int k = 0; k <= K; k++) {
            for(int n = 0; n < N; n++) {
                if(A[n] > k)
                    break;
                dp[k] = Math.min(dp[k], dp[k - A[n]] + 1);
            }
        }
        System.out.println(dp[K] == INF ? -1 : dp[K]);
    }
}