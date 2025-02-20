import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parent[n] = n;
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		st = new StringTokenizer(br.readLine());
		int answer = 0, prev = find(Integer.parseInt(st.nextToken()));
		while (st.hasMoreTokens()) {
			int curr = find(Integer.parseInt(st.nextToken()));
			if (prev != curr) {
				answer++;
				prev = curr;
			}
		}
		System.out.println(answer);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		parent[rootB] = rootA;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}
}