import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] A = new boolean[N][N];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			A[a][b] = true;
			A[b][a] = true;
		}
		StringBuilder sb = new StringBuilder();
		int time = 0;
		while (true) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (A[i][j]) continue;
					for (int k = 0; k < N; k++) {
						if (!A[i][k] || !A[j][k]) continue;
						q.offer(i * N + j);
						break;
					}
				}
			}
			if (q.size() == 0)
				break;
			sb.append(q.size()).append("\n");
			for (int v : q) {
				int a = v / N;
				int b = v % N;
				A[a][b] = true;
				A[b][a] = true;
			}
			time++;
		}
		System.out.println(time);
		System.out.println(sb);
	}
}