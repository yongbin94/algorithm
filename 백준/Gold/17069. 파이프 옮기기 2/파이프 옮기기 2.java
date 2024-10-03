import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		long[][][] memo = new long[N + 1][N + 1][3];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			while (st.hasMoreTokens())
				map[n][idx++] = Integer.parseInt(st.nextToken());
		}

		int[] dr = {0, 1, 1};
		int[] dc = {1, 1, 0};
		memo[N-1][N-1][1] = 1;
		for (int r = N - 1; r >= 0; r--) {
			for (int c = N - 1; c >= 0; c--) {
				for (int d = 0; d < 3; d++) {
					long sum = 0;
					if (r == N - 1 && c == N - 1) {
						continue;
					}
					if (map[r + dr[d]][c + dc[d]] == 1)
						continue;
					if (d == 1) { // 회전한 방향이 대각선이면 벽 체크
						if (map[r + dr[0]][c + dc[0]] == 1)
							continue;
						if (map[r + dr[2]][c + dc[2]] == 1)
							continue;
					}
					for (int i = -1; i <= 1; i++) {
						int nd = d + i; // 이동될 방향
						if ( nd < 0 || nd >= 3) // 방향이 →↘↓ 이 아님
							continue;
						int nr = r + dr[d];
						int nc = c + dc[d];
						sum += memo[nr][nc][nd];
					}
					memo[r][c][d] = sum;
				}
			}
		}
		System.out.println(memo[0][1][0] + memo[0][1][1]);
	}
}
