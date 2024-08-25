import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static long[] memo;
    static boolean[] mst, visited;
    static List<Integer>[] lists;
    static List<int[]>[] prev;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;
            edges = new Edge[M];
            mst = new boolean[M];
            visited = new boolean[N];
            lists = new ArrayList[N];
            prev = new ArrayList[N];
            for (int n = 0; n < N; n++) {
                lists[n] = new ArrayList<>();
                prev[n] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[m] = new Edge(e, w);
                lists[s].add(m);
            }
            solution();
            sb.append(memo[E] == Long.MAX_VALUE ? -1 : memo[E]).append("\n");
        }
        System.out.println(sb);
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));
        memo = new long[N];
        Arrays.fill(memo, Long.MAX_VALUE);
        memo[S] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[E] < s.w)
                break;
            if (memo[s.e] < s.w)
                continue;
            for (int idx : lists[s.e]) {
                Edge e = edges[idx];
                long w = s.w + e.w;
                if (memo[e.e] < w)
                    continue;
                else if (memo[e.e] > w) {
                    memo[e.e] = w;
                    prev[e.e] = new ArrayList<>();
                    pq.offer(new Edge(e.e, w));
                }
                prev[e.e].add(new int[]{s.e, idx});
            }
        }
        visited = new boolean[N];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(E);
        while (!q.isEmpty()) {
            int s = q.poll();
            for (int[] arr : prev[s]) {
                mst[arr[1]] = true;
                if (visited[arr[0]])
                    continue;
                visited[arr[0]] = true;
                q.offer(arr[0]);
            }
        }
        pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));
        memo = new long[N];
        Arrays.fill(memo, Long.MAX_VALUE);
        memo[S] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[E] < s.w)
                break;
            if (memo[s.e] < s.w)
                continue;
            for (int idx : lists[s.e]) {
                Edge e = edges[idx];
                long w = s.w + e.w;
                if (memo[e.e] <= w || mst[idx])
                    continue;
                memo[e.e] = w;
                pq.offer(new Edge(e.e, w));

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