import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;
	static Queue<Integer> A;
	static boolean[] V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = new boolean[1_000_001];
		A = new ArrayDeque<>();
		T = 0;
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens())
			V[Integer.parseInt(st.nextToken())] = true;
		for (int n = 1_000_000; n >= 0; n--) {
			if (V[n]) {
				A.offer(n - M);
				continue;
			}
			if (!A.isEmpty()) {
				int a = A.poll();
				if (a > n) {
					System.out.println("fail");
					return;
				}
				T++;
			} else
				T = Math.max(T - 1, 0);
		}
		System.out.println(T == 0 && A.isEmpty() ? "success" : "fail");
	}
}