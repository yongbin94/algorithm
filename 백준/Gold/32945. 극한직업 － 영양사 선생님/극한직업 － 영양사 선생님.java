import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for (int n = 0; n < N; n++)
			pq.offer(Integer.parseInt(st.nextToken()));

		int answer = 0;
		int prev = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			int curr = pq.poll();
			if (prev == 0)
				break;
			if (prev > curr)
				prev = curr;
			prev--;
			answer++;
		}
		System.out.println(answer);
	}
}