import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<Long, Integer> map = new HashMap<>();
		for (int n = 0; n < N; n++) {
			long v = Long.parseLong(br.readLine(), 2);
			map.put(v, map.getOrDefault(v, 0) + 1);
		}
		int K = Integer.parseInt(br.readLine());
		int answer = 0;
		for (long v : map.keySet()) {
			int k = M - Long.bitCount(v);
			if (K < k || K % 2 != k % 2)
				continue;
			answer = Math.max(answer, map.get(v));
		}
		System.out.println(answer);
	}
}

