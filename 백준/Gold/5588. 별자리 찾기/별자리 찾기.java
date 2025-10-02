import java.io.*;
import java.util.*;

public class Main {
	static Star[] A;
	static Star[] B;
	static Set<Long> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		A = new Star[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			A[n] = new Star(x, y);
		}
		int M = Integer.parseInt(br.readLine());
		B = new Star[M];
		set = new HashSet<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			B[m] = new Star(x, y);
			set.add(x * 10000000L + y);
		}
		outer: for (int m = 0; m < M; m++) {
			int x = B[m].x - A[0].x;
			int y = B[m].y - A[0].y;
			for (int n = 0; n < N; n++) {
				if (!set.contains((A[n].x + x) * 10000000L + A[n].y + y)) continue outer;
			}
			System.out.println(x + " " + y);
			return;
		}
	}

	private static class Star {
		int x, y;

		public Star(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}