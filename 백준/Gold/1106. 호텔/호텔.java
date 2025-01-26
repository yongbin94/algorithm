import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int C, N;
	static int[] dp;
	static List<Hotel> H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = new ArrayList<>();
		dp = new int[C + 101];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			H.add(new Hotel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(H, (o1, o2) -> o1.n - o2.n);

		int answer = INF;
		for (int c = 0; c <= C + 100; c++) {
			for (Hotel h : H) {
				if (h.n > c)
					break;
				dp[c] = Math.min(dp[c], dp[c - h.n] + h.c);
				if(c >= C)
					answer = Math.min(answer, dp[c]);
			}
		}
		
		System.out.println(answer);
	}

	private static class Hotel {
		int c, n;

		public Hotel(int c, int n) {
			this.c = c;
			this.n = n;
		}
	}
}