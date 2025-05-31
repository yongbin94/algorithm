import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static long[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new long[N][M];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				A[n][m] = Long.parseLong(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		long sum = 0;
		for (int n = 0; n < N; n++) {
			Arrays.sort(A[n]);
			long v = A[n][M / 2];
			sb.append(v).append(" ");
			for (int m = 0; m < M; m++) {
				sum += Math.abs(A[n][m] - v);
			}
		}
		System.out.println(sum);
		System.out.println(sb);
	}
}
