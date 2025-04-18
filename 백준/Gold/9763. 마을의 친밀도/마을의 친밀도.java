import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Village[] A = new Village[N];
		for (int n = 0; n < N; n++) {
			A[n] = new Village(new StringTokenizer(br.readLine()));
		}
		int res = INF;
		for (int i = 0; i < N; i++) {
			int a = INF, b = INF;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				int v = A[i].dist(A[j]);
				if (v < a) {
				    b = a;
				    a = v;
				} else if (v < b) {
				    b = v;
				}
			}
			res = Math.min(res, a + b);
		}
		System.out.println(res);
	}

	private static class Village {
		int x, y, z;

		public Village(StringTokenizer st) {
			this.x = Integer.parseInt(st.nextToken());
			this.y = Integer.parseInt(st.nextToken());
			this.z = Integer.parseInt(st.nextToken());
		}

		private int dist(Village o) {
			return Math.abs(this.x - o.x) + Math.abs(this.y - o.y) + Math.abs(this.z - o.z);
		}
	}
}