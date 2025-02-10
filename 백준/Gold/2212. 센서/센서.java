import java.io.*;
import java.util.*;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> input = new PriorityQueue<>();

		int res = 0;
		while (st.hasMoreTokens()) {
			int v = Integer.parseInt(st.nextToken());
			res = Math.max(res, v);
			input.offer(v);
		}

		int prev = input.poll();
		res = res - prev;

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		while (!input.isEmpty()) {
			int v = input.poll();
			pq.offer(v - prev);
			prev = v;
		}
		
		while (M-- > 1)
			res -= pq.isEmpty() ? 0 : pq.poll();

		System.out.println(Math.max(0, res));
	}

}
