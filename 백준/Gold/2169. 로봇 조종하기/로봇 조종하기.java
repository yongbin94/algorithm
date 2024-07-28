import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, memo1, memo2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][];
		memo1 = new int[N][M];
		memo2 = new int[N][M];
		for (int n = 0; n < N; n++)
			map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.stream(memo1).forEach(arr -> Arrays.fill(arr, Integer.MIN_VALUE));
		Arrays.stream(memo2).forEach(arr -> Arrays.fill(arr, Integer.MIN_VALUE));
		
		solution();
		System.out.println(Math.max(memo1[N-1][M-1], memo2[N-1][M-1]));
	}

	private static void solution() {
		memo1[0][0] = map[0][0];
		memo2[0][0] = map[0][0];
		for (int m = 1; m < M; m++)
			memo2[0][m] = memo2[0][m - 1] + map[0][m];
		for (int n = 1; n < N; n++) {
			for (int m = 0; m < M; m++) {
				int v = Math.max(memo1[n - 1][m], memo2[n - 1][m]) + map[n][m];
				memo1[n][m] = Math.max(memo1[n][m], v);
				memo2[n][m] = Math.max(memo2[n][m], v);
				for(int i = m - 1; i >= 0; i--) {
					v += map[n][i];
					if(v <= memo1[n][i])
						break;
					memo1[n][i] = Math.max(memo1[n][i], v);
				}
				v = Math.max(memo1[n - 1][m], memo2[n - 1][m]) + map[n][m];
				for(int i = m + 1; i < M; i++){
					v += map[n][i];
					if(v <= memo2[n][i])
						break;
					memo2[n][i] = Math.max(memo2[n][i], v);
				}
			}
		}
	}

	static class Pos {
		int r, c, w;

		public Pos(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
}
