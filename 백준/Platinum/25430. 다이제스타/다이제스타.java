import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static long[] memo;
    static List<Edge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memo = new long[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        Arrays.fill(memo, Long.MAX_VALUE);
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
        memo[S] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == E) {
                System.out.println(s.w);
                return;
            }
            if (memo[s.e] < s.w)
                continue;
            for (Edge e : list[s.e]) {
                if (s.m >= e.w)
                    continue;
                long w = s.w + e.w;
                if (memo[e.e] > w) {
                    memo[e.e] = w;
                    pq.offer(new Edge(e.e, e.w, w));
                }
            }
        }
        System.out.println("DIGESTA");
    }

    private static class Edge implements Comparable<Edge> {
        int e;
        long m, w;

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