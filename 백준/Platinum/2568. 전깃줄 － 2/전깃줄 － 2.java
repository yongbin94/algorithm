import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Line[] L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		L = new Line[N + 1];
		L[0] = new Line(0, 0);
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			L[n] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(L, (o1, o2) -> o1.s - o2.s);
		int[] lis = new int[N + 1];
		int[] index = new int[N + 1];
		int[] prev = new int[N + 1];
		Arrays.fill(lis, Integer.MAX_VALUE);
		lis[0] = 0;
		int len = 1;
		boolean[] used = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			int p = Arrays.binarySearch(lis, L[i].e);
			if (p < 0) p = -(p + 1);
			if (len == p) len++;
			lis[p] = L[i].e;
			index[p] = i;
			prev[i] = index[p - 1];
		}
		List<Integer> list = new ArrayList<>();
		for (int i = index[len - 1]; i != 0; i = prev[i])
				used[i] = true;
		for (int i = 1; i <= N; i++)
			if (!used[i])
				list.add(L[i].s);
		Collections.sort(list);
		sb.append(list.size()).append("\n");
		for (int v : list)
			sb.append(v).append("\n");
		System.out.println(sb);
	}

	private static class Line {
		int s, e;

		public Line(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
