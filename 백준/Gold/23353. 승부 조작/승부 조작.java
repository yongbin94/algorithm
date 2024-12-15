import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { 0, 1, 1, 1 };
	static int[] dc = { 1, 0, 1, -1 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] == 0)
					continue;
				for (int d = 0; d < 4; d++) {
					if (map[r - dr[d]][c - dc[d]] == 1)
						continue;
					boolean flag = map[r][c] == 2;
					int cnt = 1;
					int nr = r + dr[d];
					int nc = c + dc[d];
					while (map[nr][nc] != 0) {
						if (map[nr][nc] == 2) {
							if(flag)
								break;
							flag = true;
						}
						nr += dr[d];
						nc += dc[d];
						cnt++;
					}
					answer = Math.max(answer, cnt);
				}
			}
		}
		System.out.println(answer);
	}
}