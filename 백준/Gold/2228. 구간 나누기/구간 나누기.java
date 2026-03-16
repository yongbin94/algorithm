import java.io.*;
import java.util.*;

public class Main {
    static final int MIN = -100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[N + 1][M + 1][2];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                Arrays.fill(dp[i][j], MIN);
            }
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0][0] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0]) + A[i];
            }
        }

        System.out.println(Math.max(dp[N][M][0], dp[N][M][1]));
    }
}