import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dp;
	static List<Candy> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				System.out.println(sb);
				return;
			}
			M = Integer.parseInt(st.nextToken().replace(".", ""));
			dp = new int[M + 1];
			list = new ArrayList<>();
			while (N-- > 0) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken().replace(".", ""));
				list.add(new Candy(c, p));
			}
			for (Candy candy : list)
				for (int i = candy.p; i <= M; i++)
					dp[i] = Math.max(dp[i], dp[i - candy.p] + candy.c);

			sb.append(dp[M]).append("\n");
		}
	}
	
	private static class Candy {
		int c, p;

		public Candy(int c, int p) {
			this.c = c;
			this.p = p;
		}
	}
}