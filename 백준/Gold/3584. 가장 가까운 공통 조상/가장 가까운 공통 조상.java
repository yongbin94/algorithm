import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N + 1];
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				parent[b] = a;
			}
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			visited = new boolean[N + 1];
			while (i != 0) {
				visited[i] = true;
				i = parent[i];
			}
			while (!visited[j])
				j = parent[j];
			sb.append(j).append("\n");
		}
		System.out.println(sb);
	}
}