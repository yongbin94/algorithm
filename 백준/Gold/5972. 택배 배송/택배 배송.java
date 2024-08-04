import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] list;
    static long[] memo;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for (int n = 1; n <= N; n++)
            list[n] = new ArrayList<>();
        memo = new long[N + 1];
        visited = new boolean[N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            list[a].add(new Edge(b, w));
            list[b].add(new Edge(a, w));
        }
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        memo[1] = 0;
        Arrays.fill(memo, Long.MAX_VALUE);
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == N) {
                System.out.println(s.w);
                return;
            }
            if (visited[s.e] || memo[s.e] < s.w)
                continue;
            visited[s.e] = true;
            for (Edge e : list[s.e]) {
                if (memo[e.e] < s.w + e.w)
                    continue;
                memo[e.e] = s.w + e.w;
                pq.offer(new Edge(e.e, memo[e.e]));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e;
        long w;

        public Edge(int e, long w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }
}