import java.io.*;
import java.util.*;

public class Main {
    private static final long INF = 1_000_000_000_000_000L;
    static int N, M, K;
    static List<Edge>[] list;
    static long[] memo;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        memo = new long[N + 1];
        Arrays.fill(memo, INF);
        for (int n = 1; n <= N; n++)
            list[n] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Edge(e, w));
        }
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int input = Integer.parseInt(st.nextToken());
            memo[input] = 0;
            pq.offer(new Edge(input, 0));
        }
        solution();
        int index = 0;
        long value = 0;
        for(int n = 1;  n <= N; n++) {
            if(value < memo[n]) {
                index = n;
                value = memo[n];
            }
        }
        System.out.println(index);
        System.out.println(value);
    }

    private static void solution() {
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[s.e] < s.w)
                continue;
            for (Edge e : list[s.e]) {
                if (memo[e.e] <= s.w + e.w)
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