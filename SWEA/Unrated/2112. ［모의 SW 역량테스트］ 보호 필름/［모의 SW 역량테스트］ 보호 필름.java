import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, K, answer;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()), tc = 0;
		while (tc++ < T) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N];
			for (int n = 0; n < N; n++)
				map[n] = Integer.parseInt(br.readLine().replace(" ", ""), 2);
			answer = N;
			recur(0, 0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean check() {
		int result = 0;
		for (int i = 0; i <= N - K; i++) {
			int bit = (1 << M) - 1;
			for (int k = 0; k < K - 1; k++)
				bit &= ((1 << M) - 1) & ~(map[k + i] ^ map[k + i + 1]);
			result |= bit;
		}
		return Integer.bitCount(result) == M;
	}

	private static void recur(int idx, int cnt) {
		if (cnt >= answer)
			return;
		if (idx == N) {
			if(check())
				answer = cnt;
			return;
		}
		int org = map[idx];
		recur(idx + 1, cnt);
		map[idx] = (1 << M) - 1;
		recur(idx + 1, cnt + 1);
		map[idx] = 0;
		recur(idx + 1, cnt + 1);
		map[idx] = org;
	}

}
