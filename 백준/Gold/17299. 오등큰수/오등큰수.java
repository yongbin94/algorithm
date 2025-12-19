import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] cnt = new int[1_000_001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			cnt[A[n] = Integer.parseInt(st.nextToken())]++;
		}

		Deque<Integer> stack = new ArrayDeque<>();
		for (int n = 0; n < N; n++) {
			while (!stack.isEmpty()) {
				if (cnt[A[stack.peek()]] >= cnt[A[n]]) break;
				A[stack.pop()] = A[n];
			}
			stack.push(n);
		}

		while (!stack.isEmpty()) {
			A[stack.pop()] = -1;
		}

		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			sb.append(A[n]).append(" ");
		}

		System.out.println(sb);
	}
}