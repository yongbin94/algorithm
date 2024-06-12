import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		memo = new int[N][M];
		for (int a = 0; a < A; a++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = '1';
		}
		for (int b = 0; b < B; b++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = '2';
		}
		solution();
	}

	private static void solution() {
		memo[0][1] = 1;
		int curr = 1;
		for (int r = 1; r < N; r++) {
			boolean flag = true;
			for (int c = curr; c < M; c++) {
				if (map[r][c] == '2')
					continue;
				memo[r][c] = memo[r][c - 1];
				if(flag)
					memo[r][c] += memo[r - 1][c];
				if (map[r][c] == '1') {
					curr = c;
					flag = false;
				}
			}
		}
		System.out.println(memo[N - 1][M - 1]);
	}
}
