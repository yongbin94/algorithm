import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Info> input = new PriorityQueue<>((o1, o2) -> o1.s - o2.s);
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			input.offer(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int start = 0, end = 0, max = 0, answer = 0;
		while (!input.isEmpty()) {
			Info I = input.poll();
			int current = 0;
			if (pq.isEmpty()) {
				start = I.s;
				end = Math.max(end, I.e);
				max = 1;
				pq.offer(I.e);
				continue;
			}
			if (pq.peek() >= I.s) {
				pq.offer(I.e);
				end = Math.max(end, I.e);
				max = Math.max(max, pq.size());
				continue;
			}
			while (!pq.isEmpty() && pq.peek() < I.s) 
				current = pq.poll();

			if (pq.isEmpty() && current + 1 < I.s) {
				answer += max * (end - start + 1);
				start = I.s;
				max = 1;
			} 
			pq.offer(I.e);
			end = Math.max(end, I.e);
			max = Math.max(max, pq.size());
		}
		answer += max * (end - start + 1);
		System.out.println(answer);
	}

	private static class Info {
		int s, e;

		public Info(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
