import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] group = new ArrayList[N];
		int[][] map = new int[M][K];
		Arrays.setAll(group, g -> new ArrayList<>());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				int v = Integer.parseInt(st.nextToken()) - 1;
				map[m][k] = v;
				group[v].add(m);
			}
		}
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		boolean[] visited = new boolean[N];
		visited[0] = true;
		int time = 1;
		while (!queue.isEmpty()) {
			for (int i = 0, size = queue.size(); i < size; i++) {
				int v = queue.poll();
				if (v == N - 1) {
					System.out.println(time);
					return;
				}
				for (int g : group[v]) {
					for (int n : map[g]) {
						if (visited[n])
							continue;
						visited[n] = true;
						queue.offer(n);
					}
				}
			}
			time++;
		}
		System.out.println(-1);
	}
}
