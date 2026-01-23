import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		System.out.println(bfs(a, b));
	}

	private static int bfs(int start, int target) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] v = new boolean[target + 1][2];

		q.offer(new int[] { start, 0, 0 });
		v[start][0] = true;

		while (!q.isEmpty()) {
			int[] c = q.poll();
			int curr = c[0];
			int cnt = c[1];
			int chance = c[2];

			if (curr == target)
				return cnt;

			if (curr + 1 <= target && !v[curr + 1][chance]) {
				v[curr + 1][chance] = true;
				q.offer(new int[] { curr + 1, cnt + 1, chance });
			}

			if (curr * 2 <= target && !v[curr * 2][chance]) {
				v[curr * 2][chance] = true;
				q.offer(new int[] { curr * 2, cnt + 1, chance });
			}

			if (chance == 0) {
				if (curr * 10 <= target && !v[curr * 10][1]) {
					v[curr * 10][1] = true;
					q.offer(new int[] { curr * 10, cnt + 1, 1 });
				}
			}
		}

		return -1;
	}
}