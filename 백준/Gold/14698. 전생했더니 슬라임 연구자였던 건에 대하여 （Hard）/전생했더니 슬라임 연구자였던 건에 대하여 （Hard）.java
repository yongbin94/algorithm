import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			PriorityQueue<Long> pq = new PriorityQueue<>();
			br.readLine();
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens())
				pq.offer(Long.parseLong(st.nextToken()));
			long res = 1;
			while (pq.size() > 1) {
				long v = pq.poll() * pq.poll();
				pq.offer(v);
				res = (res * (v % MOD)) % MOD;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}
