import java.io.*;
import java.util.*;

public class Main {
	static int N, T, G;
	static boolean[] V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		V = new boolean[100_000];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		V[N] = true;
		for (int t = 0; t <= T; t++) {
			for (int i = 0, size = q.size(); i < size; i++) {
				int n = q.poll();
				if (n == G) {
					System.out.println(t);
					return;
				}
				if (n + 1 < 100_000 && !V[n + 1]) {
					q.offer(n + 1);
					V[n + 1] = true;
				}
				if (n * 2 < 100_000) {
					int tmp = 1;
					while (n * 2 >= tmp)
						tmp *= 10;
					tmp = n * 2 - tmp / 10;
					if (tmp <= 100_000 && !V[tmp]) {
						q.offer(tmp);
						V[tmp] = true;
					}
				}
			}
		}
		System.out.println("ANG");
	}
}
