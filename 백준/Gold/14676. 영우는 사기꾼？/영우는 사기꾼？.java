import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Integer>[] out = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			out[i] = new ArrayList<>();

		int[] need = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			out[x].add(y);
			need[y]++;
		}

		int[] cnt = new int[N + 1];
		int[] sat = new int[N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			if (cmd == 1) {
				if (sat[a] != need[a]) {
					System.out.println("Lier!");
					return;
				}

				if (cnt[a] == 0) {
					for (int next : out[a]) {
						sat[next]++;
					}
				}
				cnt[a]++;

			} else {
				if (cnt[a]-- == 0) {
					System.out.println("Lier!");
					return;
				}
                
				if (cnt[a] == 0) {
					for (int next : out[a]) {
						sat[next]--;
					}
				}
			}
		}

		System.out.println("King-God-Emperor");
	}
}