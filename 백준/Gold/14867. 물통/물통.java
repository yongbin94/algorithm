import java.io.*;
import java.util.*;

public class Main {
	static final int LMT = 1_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		long D = Long.parseLong(st.nextToken());
		long G = C * LMT + D;
		Set<Long> V = new HashSet<>();
		Queue<Long> q = new ArrayDeque<>();
		q.add(0L);
		V.add(0L);
		for (int cnt = 0; !q.isEmpty(); cnt++) {
			for (int i = q.size(); i > 0; i--) {
				long v = q.poll();
				if (v == G) {
					System.out.println(cnt);
					return;
				}
				long a = v / LMT;
				long b = v % LMT;
				
				if (V.add(b)) q.add(b);
				if (V.add(a * LMT)) q.offer(a * LMT);
				if (V.add(A * LMT + b)) q.add(A * LMT + b);
				if (V.add(a * LMT + B)) q.offer(a * LMT + B);
				long ta = Math.min(a + b, A);
				long tb = b - Math.max(ta - a, 0);
				if (V.add(ta * LMT + tb)) q.offer(ta * LMT + tb);
				tb = Math.min(b + a, B);
				ta = a - Math.max(tb - b, 0);
				if (V.add(ta * LMT + tb)) q.offer(ta * LMT + tb);
			}
		}
		System.out.println(-1);
	}
}