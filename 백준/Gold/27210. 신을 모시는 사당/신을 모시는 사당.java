import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), answer = 0;
        int[][] dp = new int[2][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            int d = Integer.parseInt(st.nextToken()) - 1;
            dp[d][n] = dp[d][n - 1] + 1;
            dp[(d + 1) & 1][n] = Math.max(dp[(d + 1) & 1][n - 1] - 1, 0);
            answer = Math.max(answer, dp[d][n]);
            answer = Math.max(answer, dp[(d + 1) & 1][n]);
        }
        System.out.println(answer);
    }
}