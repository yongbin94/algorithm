import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Info> A = new PriorityQueue<>();
		int answer = 0;
		while (N-- > 0) A.offer(new Info(new StringTokenizer(br.readLine())));
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int d = A.peek().d; d > 0; d--) {
			while (!A.isEmpty() && A.peek().d == d) pq.offer(A.poll().w);
			if (!pq.isEmpty()) answer += pq.poll();
		}
		System.out.println(answer);
	}

	private static class Info implements Comparable<Info> {
		int d, w;

		public Info(StringTokenizer st) {
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
		}

		@Override
		public int compareTo(Info o) {
			return o.d - this.d;
		}

	}
}
