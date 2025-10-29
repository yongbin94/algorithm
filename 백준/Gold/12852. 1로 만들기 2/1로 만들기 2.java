import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		int[] A = new int[N + 1];
		Arrays.fill(A, -1);
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		while (!q.isEmpty()) {
			int v = q.poll();
			if (v == 1) {
				break;
			}
			if (v % 3 == 0 && A[v / 3] == -1) {
				A[v / 3] = v;
				q.offer(v / 3);
			}
			if (v % 2 == 0 && A[v / 2] == -1) {
				A[v / 2] = v;
				q.offer(v / 2);
			}
			if (A[v - 1] == -1) {
				A[v - 1] = v;
				q.offer(v - 1);
			}
		}
		Stack<Integer> s = new Stack<>();
		int v = 1;
		while (v != -1) {
			s.push(v);
			v = A[v];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(s.size() - 1).append("\n");
		while (!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
		}
		System.out.println(sb);
	}
}
