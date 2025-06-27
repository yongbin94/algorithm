import java.util.*;

public class Main {
	static int N;
	static double answer;
	static double[] A;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new double[4];
		visited = new boolean[N * 2 + 1][N * 2 + 1];
		visited[N][N] = true;
		for (int d = 0; d < 4; d++) {
			A[d] = sc.nextInt() / 100.0;
		}
		dfs(N, N, 0, 1.0);
		System.out.printf("%.10f\n", answer);
	}

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

    private static void dfs(int r, int c, int n, double w) {
        if (n == N) {
            answer += w;
            return;
        }
        for (int d = 0; d < 4; d++) {
            if (A[d] == 0) continue;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc, n + 1, w * A[d]);
            visited[nr][nc] = false;
        }
    }
}