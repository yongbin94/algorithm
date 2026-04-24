import java.io.*;
import java.util.*;

public class Main {
	static int[][] gear;
	static int[] top;
	static boolean[] visited;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		gear = new int[T][];
		top = new int[T];

		for (int i = 0; i < T; i++) {
			gear[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		int N = Integer.parseInt(br.readLine()), n = 0;
		while (n++ < N) {
			visited = new boolean[T];
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			rotation(idx - 1, r * -1);
		}

		int answer = 0;
		for (int i = 0; i < T; i++) {
			if (gear[i][top[i]] == 1) answer++;
		}
		System.out.println(answer);
	}

	private static void rotation(int idx, int r) {
		visited[idx] = true;
		if (idx < T - 1 && !visited[idx + 1]) {
			if (gear[idx][(top[idx] + 2) % 8] != gear[idx + 1][(top[idx + 1] + 6) % 8]) {
				rotation(idx + 1, r * -1);
			}
		}
		if (idx > 0 && !visited[idx - 1]) {
			if (gear[idx][(top[idx] + 6) % 8] != gear[idx - 1][(top[idx - 1] + 2) % 8]) {
				rotation(idx - 1, r * -1);
			}
		}
		top[idx] = (top[idx] + r + 8) % 8;
	}
}