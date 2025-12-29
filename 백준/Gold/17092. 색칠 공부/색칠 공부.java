import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long H = Long.parseLong(st.nextToken());
		long W = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		Map<Long, Integer> map = new HashMap<>();

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			long r = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			for (long i = r - 2; i <= r; i++) {
				if (i < 1 || i > H - 2) continue;
				for (long j = c - 2; j <= c; j++) {
					if (j < 1 || j > W - 2) continue;
					long key = i * 1_000_000_001L + j;
					map.put(key, map.getOrDefault(key, 0) + 1);
				}
			}
		}

		long[] A = new long[10];
		for (int v : map.values()) {
			A[v]++;
		}

		A[0] = (H - 2) * (W - 2) - map.size();

		StringBuilder sb = new StringBuilder();
		for (long v : A) {
			sb.append(v).append('\n');
		}
		System.out.print(sb);
	}
}