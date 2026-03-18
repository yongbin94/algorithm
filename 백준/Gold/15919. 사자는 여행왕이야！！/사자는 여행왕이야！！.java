import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<Integer>[] list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[e].add(s);
		}

		int[] dp = new int[N + 1];
		Arrays.fill(dp, 1001);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int s : list[i]) {
				for (int j = 0; j < s; j++) {
					dp[i] = Math.min(dp[i], Math.max(dp[j], s - j - 1));
				}
			}
		}

		int res = 1001;
		for (int i = 0; i <= N; i++) {
			res = Math.min(res, Math.max(dp[i], N - i));
		}

		System.out.println(res);
	}
}