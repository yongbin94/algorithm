import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<CR> list = new ArrayList<>();
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			list.add(new CR(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int prev = 0;
		int time = 0;
		for(CR cr : list) {
			if(time < cr.d || prev < cr.d) {
				time++;
				prev = cr.d;
				pq.offer(cr.q);
				continue;
			}
			if(pq.peek() >= cr.q)
				continue;
			pq.poll();
			pq.offer(cr.q);
		}
		int sum = 0;
		while(!pq.isEmpty())
			sum += pq.poll();
		System.out.println(sum);
	}
	private static class CR implements Comparable<CR> {
		int d, q;
		public CR(int d, int q) {
			this.d = d;
			this.q = q;
		}
		@Override
		public int compareTo(CR o) {
			return this.d == o.d ? o.q - this.q : this.d - o.d;
		}
	}
}
