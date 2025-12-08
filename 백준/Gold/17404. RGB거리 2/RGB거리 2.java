import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = Integer.MAX_VALUE;
        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int s = 0; s < 3; s++) {
            int[][] dp = new int[N][3];
            for (int i = 0; i < 3; i++) {
                if (i == s) dp[0][i] = cost[0][i];
                else dp[0][i] = 1_000_000_000;
            }

            for (int n = 1; n < N; n++) {
                dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + cost[n][0];
                dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + cost[n][1];
                dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + cost[n][2];
            }

            for (int e = 0; e < 3; e++) {
                if (e == s) continue;
                answer = Math.min(answer, dp[N - 1][e]);
            }
        }
        System.out.println(answer);
    }
}