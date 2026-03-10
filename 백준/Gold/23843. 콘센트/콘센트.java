import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			pq.offer(0);
		}

		for (int n = N - 1; n >= 0; n--) {
			pq.offer(pq.poll() + A[n]);
		}

		int res = 0;
		while (!pq.isEmpty()) {
			res = pq.poll();
		}

		System.out.println(res);
	}
}