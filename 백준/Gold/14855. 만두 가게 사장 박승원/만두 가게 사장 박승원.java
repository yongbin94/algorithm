import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[] dp = new int[N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int cnt = a / b;

            for (int n = N; n >= 0; n--) {
                for (int i = 1; i <= cnt; i++) {
                    int w = i * c;
                    if (w > n) break;
                    dp[n] = Math.max(dp[n], dp[n - w] + d * i);
                }
            }
        }

        for (int n = C; n <= N; n++) {
            dp[n] = Math.max(dp[n], dp[n - C] + D);
        }

		System.out.println(dp[N]);
	}
}