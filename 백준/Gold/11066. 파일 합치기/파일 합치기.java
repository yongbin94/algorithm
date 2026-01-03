import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N + 1];
            int[][] dp = new int[N + 1][N + 1];
            for (int n = 1; n <= N; n++) {
                A[n] = A[n - 1] + Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < N; i++) {
                for (int l = 1; l + i <= N; l++) {
                    int r = l + i;
                    dp[l][r] = Integer.MAX_VALUE;

                    for (int mid = l; mid < r; mid++) {
                        dp[l][r] = Math.min(dp[l][r],
                                dp[l][mid] + dp[mid + 1][r] + (A[r] - A[l - 1]));
                    }
                }
            }
            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb);
    }
}