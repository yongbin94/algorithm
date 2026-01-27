import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[j] = (dp[j] + dp[j - 1]) % P;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 2; j <= K; j++) {
                dp[j] = (dp[j] + dp[j - 2]) % P;
            }
        }

        System.out.println(dp[K]);
    }
}
