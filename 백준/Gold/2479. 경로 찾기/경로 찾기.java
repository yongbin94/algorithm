import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[][] isAdj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String[] S = new String[N + 1];
		for (int n = 1; n <= N; n++) {
			S[n] = br.readLine();
		}

		isAdj = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int cnt = 0;
				for (int k = 0; k < K; k++) {
					if (S[i].charAt(k) != S[j].charAt(k)) cnt++;
					if (cnt > 1) break;
				}
				if (cnt == 1) isAdj[i][j] = isAdj[j][i] = true;
			}
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		System.out.println(solution(start, end));
	}

	private static String solution(int start, int end) {
		int[] prev = new int[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		prev[start] = -1;
		
		boolean flag = false;
		while (!q.isEmpty()) {
			int u = q.poll();
			if (u == end) {
				flag = true;
				break;
			}

			for (int v = 1; v <= N; v++) {
				if (isAdj[u][v] && prev[v] == 0) {
					prev[v] = u;
					q.offer(v);
				}
			}
		}

		if (!flag) return "-1";
		
		Deque<Integer> stack = new ArrayDeque<>();
		int temp = end;
		while (temp != -1) {
			stack.push(temp);
			temp = prev[temp];
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		return sb.toString();
	}
}