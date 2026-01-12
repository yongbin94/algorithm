import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[] disabled = new boolean[N + 1];
		while (M-- > 0) {
			disabled[Integer.parseInt(br.readLine())] = true;
		}

		boolean[][] v = new boolean[N + 1][150];
		Queue<int[]> q = new LinkedList<>();

		if (!disabled[2]) {
			q.offer(new int[] { 2, 1, 1 });
			v[2][1] = true;
		}

		int answer = -1;
		while (!q.isEmpty()) {
			int[] c = q.poll();

			if (c[0] == N) {
				answer = c[2];
				break;
			}

			int d = -1;
			while (d <= 1) {
				int ns = c[1] + d++;
				int np = c[0] + ns;

				if (ns <= 0 || np > N || disabled[np] || v[np][ns])
					continue;

				v[np][ns] = true;
				q.offer(new int[] { np, ns, c[2] + 1 });
			}
		}
		System.out.println(answer);
	}
}