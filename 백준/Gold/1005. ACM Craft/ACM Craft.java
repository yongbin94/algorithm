import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()), tc = 0;
		outer : while(tc++ < T) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] D = Arrays.stream(("0 "+br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] in = new int[N+1];
			List<Integer>[] list = new ArrayList[N+1];
			for(int i = 1; i <= N; i++)
				list[i] = new ArrayList<>();
			
			for(int i = 0; i< K; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				list[s].add(e);
				in[e]++;
			}
			int W = Integer.parseInt(br.readLine());
			
			PriorityQueue<Info> pq = new PriorityQueue<>();
			for(int i = 1; i <= N; i++)
				if(in[i] == 0)
					pq.offer(new Info(i, D[i]));
			
			int time = 0;
			while(!pq.isEmpty()) {
				time = pq.peek().t;
				while(pq.peek().t == time) {
					Info info = pq.poll();
					if(info.e == W) {
						sb.append(time).append("\n");
						continue outer;
					}
					for(int e : list[info.e]) {
						if(--in[e] == 0)
							pq.offer(new Info(e, time + D[e]));
					}
				}
				
			}
		}
		System.out.println(sb);
	}
	static class Info implements Comparable<Info>{
		int e, t;

		public Info(int e, int t) {
			this.e = e;
			this.t = t;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.t, o.t);
		}

		@Override
		public String toString() {
			return "Info [e=" + e + ", t=" + t + "]";
		}
		
	}
}
