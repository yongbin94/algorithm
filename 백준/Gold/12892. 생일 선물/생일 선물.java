import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		Gift[] G = new Gift[N];
		for (int n = 0; n < N; n++) {
			G[n] = new Gift(new StringTokenizer(br.readLine()));
		}
		Arrays.sort(G, (o1, o2) -> o2.p - o1.p);
		long sum = 0, answer = 0;
		for (int l = 0, r = 0; r < N; l++) {
			while (r < N && G[l].p - G[r].p < D) {
				sum += G[r++].v;
			}
			answer = Math.max(answer, sum);
			sum -= G[l].v;
		}
		System.out.println(answer);
	}

	private static class Gift {
		int p, v;

		public Gift(StringTokenizer st) {
			p = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
		}
	}
}