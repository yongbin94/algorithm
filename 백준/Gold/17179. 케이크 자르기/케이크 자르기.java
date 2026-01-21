import java.io.*;
import java.util.*;

public class Main {
	static int N, M, L;
	static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		S = new int[M];
		for (int i = 0; i < M; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}

		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int Q = Integer.parseInt(br.readLine());
			sb.append(binarySearch(Q)).append("\n");
		}
		System.out.print(sb);
	}

	private static int binarySearch(int Q) {
		int l = 1, r = L;

		while (l <= r) {
			int mid = (l + r) / 2;
			if (check(mid, Q)) l = mid + 1;
			else r = mid - 1;
		}
		return r;
	}

	private static boolean check(int v, int Q) {
		int x = 0, i = 0, prev = 0;

		while (i < M) {
			if (S[i] - prev >= v) {
				prev = S[i];
				x++;
			}
			i++;
		}
		return x == Q ? L - prev >= v : x > Q;
	}
}