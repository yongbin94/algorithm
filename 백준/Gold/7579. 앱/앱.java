import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A, C;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][10001];
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        C = new int[N];
        for (int n = 0; n < N; n++)
            C[n] = Integer.parseInt(st.nextToken());
        solution();
    }

    private static void solution() {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 10001; i++)
            if (i >= C[0])
                if ((dp[0][i] = A[0]) >= M) answer = Integer.min(answer, i);
        for (int n = 1; n < N; n++) {
            int a = A[n];
            int c = C[n];
            for (int i = 0; i < 10001; i++) {
                dp[n][i] = i < c ? dp[n - 1][i] : Math.max(dp[n - 1][i], dp[n - 1][i - c] + a);
                if(dp[n][i] >= M) answer = Integer.min(answer, i);
            }
        }
        System.out.println(answer);
    }
}