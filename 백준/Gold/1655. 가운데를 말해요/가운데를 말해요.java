import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> S = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> L = new PriorityQueue<>();
		S.offer(Integer.parseInt(br.readLine()));
		sb.append(S.peek()).append("\n");
		for (int n = 1; n < N; n++) {
			int v = Integer.parseInt(br.readLine());
			if(S.size() == L.size()) {
				if(v > L.peek()) {
					S.offer(L.poll());
					L.offer(v);
				} else {
					S.offer(v);
				}
			} else {
				if(v < S.peek()) {
					L.offer(S.poll());
					S.offer(v);
				} else {
					L.offer(v);
				}
			}
			sb.append(S.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
