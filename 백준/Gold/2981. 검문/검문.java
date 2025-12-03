import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(A);
		int v = A[1] - A[0];
		for (int i = 2; i < N; i++) {
			v = gcd(v, A[i] - A[i - 1]);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(v);
		for (int i = 2; i * i <= v; i++) {
			if (v % i == 0) {
				pq.offer(i);
				if (i != v / i) pq.offer(v / i);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append(" ");
		}
		System.out.println(sb);
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
}