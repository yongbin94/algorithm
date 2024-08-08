import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt;
    static int[] memo;
    static boolean[] visited;
    static List<Edge>[] list;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        memo = new int[N + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Edge(e, 0));
            memo[e] = 0;
        }
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, w));
            list[b].add(new Edge(a, w));
        }
        solution();
    }

    private static void solution() {
        while(!pq.isEmpty()) {
            Edge s = pq.poll();
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            if(++cnt == N) {
                System.out.println(s.e);
                return;
            }
            for(Edge e : list[s.e]) {
                int w = s.w + e.w;
                if(visited[e.e] || memo[e.e] <= w)
                    continue;
                memo[e.e] = w;
                pq.add(new Edge(e.e, w));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w == o.w ? o.e - this.e : this.w - o.w;
        }
    }
}