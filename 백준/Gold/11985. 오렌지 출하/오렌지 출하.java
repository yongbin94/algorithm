import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long[] A = new long[N + 1];
        long[] dp = new long[N + 1];
        for (int n = 1; n <= N; n++) {
            A[n] = Long.parseLong(br.readLine());
            long max = A[n];
            long min = A[n];
            dp[n] = dp[n - 1] + K;
            for (int m = 2; m <= Math.min(n, M); m++) {
                max = Math.max(max, A[n - m + 1]);
                min = Math.min(min, A[n - m + 1]);
                dp[n] = Math.min(dp[n], dp[n - m] + K + m * (max - min));
            }
        }
        System.out.println(dp[N]);
    }
}
