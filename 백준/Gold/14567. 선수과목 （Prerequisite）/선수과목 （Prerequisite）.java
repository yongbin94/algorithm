import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] in = new int[N + 1];
		int[] answer = new int[N + 1];
		List<Integer>[] list = new ArrayList[N + 1];
		for (int n = 1; n <= N; n++)
			list[n] = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[A].add(B);
			in[B]++;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int n = 1; n <= N; n++)
			if (in[n] == 0)
				q.add(n);
		int time = 0;
		while (!q.isEmpty()) {
			time++;
			for (int i = 0, size = q.size(); i < size; i++) {
				int v = q.poll();
				answer[v] = time;
				for(int n : list[v])
					if(--in[n] == 0)
						q.add(n);
			}
		}
		for(int n = 1; n <= N; n++)
			sb.append(answer[n]).append(" ");
		System.out.println(sb);
	}
}
