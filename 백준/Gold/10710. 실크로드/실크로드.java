import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] D = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.parseInt(br.readLine());
        }

        int[] C = new int[M + 1];
        for (int j = 1; j <= M; j++) {
            C[j] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][M + 1];
        
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], 1_000_000_001);
        }

        for (int j = 0; j <= M; j++) {
            dp[0][j] = 0;
        }

        for (int j = 1; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + (D[i] * C[j]));
            }
        }

        System.out.println(dp[N][M]);
    }
}