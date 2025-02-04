import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp, prev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Block[] list = new Block[N + 1];
		list[0] = new Block(0, 100_000, 0, 100_000);
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			list[n] = new Block(n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(list);
		dp = new int[N + 1];
		prev = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			Block block = list[n];
			for (int i = 0; i < n; i++) {
				if (list[n].v > list[i].v)
					continue;
				if (dp[n] < dp[i] + block.h) {
					dp[n] = dp[i] + block.h;
					prev[n] = i;
				}
			}
		}
		int max = 0;
		int idx = 0;
		for (int n = 1; n <= N; n++) {
			if (max < dp[n]) {
				max = dp[n];
				idx = n;
			}

		}
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (idx != 0) {
			sb.append(list[idx].n).append("\n");
			idx = prev[idx];
			cnt++;
		}
		System.out.println(cnt);
		System.out.println(sb);
	}

	private static class Block implements Comparable<Block> {
		int n, w, h, v;

		public Block(int n, int w, int h, int v) {
			this.n = n;
			this.w = w;
			this.h = h;
			this.v = v;
		}

		@Override
		public int compareTo(Block o) {
			return o.w - this.w;
		}
	}
}