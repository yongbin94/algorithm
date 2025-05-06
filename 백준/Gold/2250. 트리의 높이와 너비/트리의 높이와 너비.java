import java.io.*;
import java.util.*;

public class Main {
	static Node[] A;
	static boolean[] isRoot;
	static int[] min;
	static int idx, level, width;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = new Node[N + 1];
		min = new int[N + 1];
		isRoot = new boolean[N + 1];
		Arrays.fill(isRoot, true);
		for (int n = 0; n < N; n++) {
			new Node(new StringTokenizer(br.readLine()));
		}
		for (int n = 1; n <= N; n++) {
			if (isRoot[n]) {
				A[n].dfs(1);
				break;
			}
		}
		System.out.printf("%s %s\n", level, width);
	}

	private static class Node {
		int n, a, b;

		public Node(StringTokenizer st) {
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			A[n] = this;
			if (a != -1) isRoot[a] = false;
			if (b != -1) isRoot[b] = false;
		}

		public void dfs(int i) {
			if (a != -1) A[a].dfs(i + 1);
			++idx;
			if (min[i] == 0) min[i] = idx;
			int w = idx - min[i] + 1;
			if (w > width || (w == width && i < level)) {
				width = w;
				level = i;
			}
			if (b != -1) A[b].dfs(i + 1);
		}
	}
}