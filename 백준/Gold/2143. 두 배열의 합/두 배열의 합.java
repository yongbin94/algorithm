import java.io.*;
import java.util.*;

public class Main {
	static long[] A, B;
	static Map<Long, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long T = Long.parseLong(br.readLine());
		int N = Integer.parseInt(br.readLine());
		long answer = 0;
		A = new long[N + 1];
		map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			A[n] = A[n - 1] + Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				long v = A[n] - A[i];
				map.put(v, map.getOrDefault(v, 0) + 1);
			}
		}
		int M = Integer.parseInt(br.readLine());
		B = new long[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int m = 1; m <= M; m++) {
			B[m] = B[m - 1] + Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				long v = B[m] - B[i];
				answer += map.getOrDefault(T - v, 0);
			}
		}
		System.out.println(answer);
	}
}