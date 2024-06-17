import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Integer> list[];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int n = 0; n < N; n++)
			list[n] = new ArrayList<>();
		visited = new boolean[N];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		for (int n = 0; n < N; n++) {
			visited[n] = true;
			if (recur(n, 0))
				return;
			visited[n] = false;
		}
		System.out.println(0);
	}

	private static boolean recur(int n, int cnt) {
		if (++cnt == 5) {
			System.out.println(1);
			return true;
		}
		for (int v : list[n]) {
			if (visited[v])
				continue;
			visited[v] = true;
			if(recur(v, cnt))
				return true;
			visited[v] = false;
		}
		return false;
	}
}
