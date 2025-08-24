import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long[] A = new long[N + 1];
			for (int n = 1; n <= N; n++) {
				A[n] = Long.parseLong(st.nextToken());
			}
			Arrays.sort(A);
			for (int n = 1; n <= N; n++) {
				A[n] += A[n - 1];
			}
			long res = 0;
			for (int m = 1; m <= N; m++) {
				long min = Long.MAX_VALUE;
				for (int i = 0; i <= N - m; i++) {
					min = Math.min(min, (A[i + m] - A[i + m - 1]) * m - (A[i + m] - A[i]));
				}
				res += min;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}
