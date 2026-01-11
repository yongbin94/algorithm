import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        if (K == 0) {
            System.out.println(0);
            return;
        }

        long[] dp = new long[K + N + 2];
        dp[K] = 1;

        int n = 0;
        while (n < N) {
            long[] tmp = new long[K + N + 2];
            int i = 1;
            while (i <= K + N) {
                if (dp[i] > 0) {
                    tmp[i + 1] += dp[i];
                    if (i - 1 > 0) tmp[i - 1] += dp[i];
                }
                i++;
            }
            dp = tmp;
            n++;
        }

        long answer = 0;
        int i = 1;
        while (i < dp.length) {
            answer += dp[i];
            i++;
        }

        System.out.println(answer);
    }
}