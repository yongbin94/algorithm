import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static long[] memo;
    static boolean[][] visited;
    static List<Edge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            list[s].add(new Edge(e, 0, w));
            list[e].add(new Edge(s, 0, w));
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0, 0));
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == E) {
                System.out.println(s.w);
                return;
            }
            for (Edge e : list[s.e]) {
                if (s.m >= e.w || visited[s.e][e.e])
                    continue;
                visited[s.e][e.e] = true;
                visited[e.e][s.e] = true;
                long w = s.w + e.w;
                pq.offer(new Edge(e.e, e.w, w));
            }
        }
        System.out.println("DIGESTA");
    }

    private static class Edge implements Comparable<Edge> {
        int e;
        long w, m;

        public Edge(int e, long m, long w) {
            this.e = e;
            this.m = m;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.m == o.m ? Long.compare(this.w, o.w) : Long.compare(this.m, o.m);
        }
    }
}
