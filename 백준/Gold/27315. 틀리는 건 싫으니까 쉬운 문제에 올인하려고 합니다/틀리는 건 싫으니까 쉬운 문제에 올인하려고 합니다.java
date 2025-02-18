import java.io.*;
import java.util.*;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		PriorityQueue<Problem> input = new PriorityQueue<>();
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if (st.nextToken().charAt(0) == '1')
				p = 0;
			input.offer(st.nextToken().charAt(0) == '1' ? new Problem((d + 1) / 2, p / 2) : new Problem(d, p));
		}
		st = new StringTokenizer(br.readLine());
		int hd = Integer.parseInt(st.nextToken());
		int hp = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		long answer = 0;
		while (M-- > 0) {
			while (!input.isEmpty() && input.peek().d <= hd) {
				pq.offer(input.poll().p);
			}
			if (pq.isEmpty()) {
				System.out.println(-1);
				return;
			}
			int p = pq.poll();
			answer += Math.max(0, p - hp);
			hp++;
			hd++;
		}
		System.out.println(answer);
	}

	private static class Problem implements Comparable<Problem> {
		int d, p;

		public Problem(int d, int p) {
			this.d = d;
			this.p = p;
		}

		@Override
		public int compareTo(Problem o) {
			return this.d - o.d;
		}
	}
}