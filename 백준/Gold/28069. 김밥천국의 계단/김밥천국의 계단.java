import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		sc.close();
		boolean[] visited = new boolean[N + 1];
		visited[0] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0);
		for (int k = 0; k <= K; k++) {
			for (int i = 0, size = q.size(); i < size; i++) {
				int v = q.poll();
				if (v == N) {
					System.out.println("minigimbob");
					return;
				}
				if (!visited[v + 1]) {
					visited[v + 1] = true;
					q.offer(v + 1);
				}
				v += v / 2;
				if (v <= N && !visited[v]) {
					visited[v] = true;
					q.offer(v);
				}
			}
		}
		System.out.println("water");
	}
}
