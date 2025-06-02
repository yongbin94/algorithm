import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        dp = new int[N][M];
        Arrays.stream(dp).forEach(v -> Arrays.fill(v, -1));
        dp[0][0] = 0;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (a >= b) continue;
            A[a][b] = Math.max(A[a][b], c);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (A[i][j] <= 0) continue;
                for (int m = 0; m < M - 1; m++) {
                    if (dp[i][m] < 0) continue;
                    dp[j][m + 1] = Math.max(dp[j][m + 1], dp[i][m] + A[i][j]);
                }
            }
        }
        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }
}