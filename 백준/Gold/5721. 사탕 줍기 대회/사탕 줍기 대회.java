import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line == null || line.isEmpty()) break;

			StringTokenizer st = new StringTokenizer(line);
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (M == 0 && N == 0) break;

			A = new int[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int[] tmp = new int[N];
				for (int j = 0; j < N; j++) {
					tmp[j] = Integer.parseInt(st.nextToken());
				}
				A[i] = solution(tmp, N);
			}

			sb.append(solution(A, M)).append("\n");
		}
		System.out.println(sb);
	}

	private static int solution(int[] arr, int size) {
		if (size == 0) return 0;
		if (size == 1) return arr[0];

		int[] dp = new int[size];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);

		for (int i = 2; i < size; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
		}

		return dp[size - 1];
	}
}