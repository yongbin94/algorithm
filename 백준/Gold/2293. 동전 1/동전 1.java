import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] A;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        dp = new int[K + 1];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine());
        for (int a : A)
            for (int i = 1; i <= K; i++) {
                if (i == a)
                    dp[i]++;
                else if (i > a)
                    dp[i] += dp[i - a];
            }
        System.out.println(dp[K]);
    }
}