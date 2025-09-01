import java.io.*;
import java.util.*;

public class Main {
	static int H, W, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		long[][][] dp = new long[L][H][W];
		for (int h = 0; h < H; h++) {
			map[h] = br.readLine().toCharArray();
		}
		char[] A = br.readLine().toCharArray();
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (map[h][w] != A[0]) continue;
				dp[0][h][w]++;
			}
		}
		int[] dr = { -1, 0, 1, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 1, 0, -1, -1, 1, -1, 1 };
		for (int l = 1; l < L; l++) {
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (map[h][w] != A[l]) continue;
					for (int d = 0; d < 8; d++) {
						int nr = h + dr[d];
						int nc = w + dc[d];
						if (!isIn(nr, nc)) continue;
						dp[l][h][w] += dp[l - 1][nr][nc];
					}
				}
			}
		}
		System.out.println(Arrays.stream(dp[L - 1]).flatMapToLong(Arrays::stream).sum());
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}