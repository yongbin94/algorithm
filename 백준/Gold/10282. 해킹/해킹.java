import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			List<Virus>[] list = new ArrayList[N + 1];
			for(int n = 1; n <= N; n++)
				list[n] = new ArrayList<>();
			while(D-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Virus(a, s));
			}
			PriorityQueue<Virus> pq = new PriorityQueue<>();
			pq.offer(new Virus(C, 0));
			boolean[] visited = new boolean[N + 1];
			int answer = 0;
			int count = 0;
			while(!pq.isEmpty()) {
				Virus v = pq.poll();
				if(visited[v.a])
					continue;
				visited[v.a] = true;
				count++;
				answer = Math.max(answer, v.s);
				for(Virus nv : list[v.a]) {
					if(visited[nv.a])
						continue;
					pq.offer(new Virus(nv.a, v.s + nv.s));
				}
			}
			sb.append(count).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	private static class Virus implements Comparable<Virus> {
		int a, s;

		public Virus(int b, int s) {
			this.a = b;
			this.s = s;
		}
		@Override
		public int compareTo(Virus o) {
			return this.s - o.s;
		}
	}
}