import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] C = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());
            C[i] = (int) ((a * m * 96) / 60000);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c1 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int[] dp = new int[c2 + 1];
        dp[0] = 1;

        for (int v : C) {
            for (int j = c2; j >= v; j--) {
                dp[j] = (dp[j] + dp[j - v]) % MOD;
            }
        }

        long answer = 0;
        for (int i = c1; i <= c2; i++) {
            answer = (answer + dp[i]) % MOD;
        }

        System.out.println(answer);
    }
}