import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static long[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new long[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		for (int k = 0; k < N; k++)
			for (int r = 0; r < N; r++)
				for (int c = 0; c < N; c++)
					map[r][c] = Math.min(map[r][c], map[r][k] + map[k][c]);

		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			sb.append(c >= map[a][b] ? "Enjoy other party" : "Stay here").append("\n");
		}
		System.out.println(sb);
	}
}