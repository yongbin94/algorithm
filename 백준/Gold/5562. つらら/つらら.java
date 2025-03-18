import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		long[] A = new long[N + 2];
		for (int n = 1; n <= N; n++) {
			pq.offer(new int[] { n, Integer.parseInt(br.readLine()) });
		}
		while (!pq.isEmpty()) {
			int[] v = pq.poll();
			A[v[0]] = Math.max(A[v[0] - 1], A[v[0] + 1]) + L - v[1];
		}
		System.out.println(Arrays.stream(A).max().getAsLong());
	}
}