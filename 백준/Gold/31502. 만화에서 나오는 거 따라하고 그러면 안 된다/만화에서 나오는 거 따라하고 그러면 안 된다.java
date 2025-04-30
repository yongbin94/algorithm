import java.io.*;
import java.util.*;

public class Main {
    static int N, M, A, B, C;
    static List<Edge>[] edges;
    static int[] degree;
    static boolean[] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        degree = new int[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        Arrays.setAll(degree, v -> edges[v].size());
        
        getRoute();
        System.out.println(find());
    }

    private static void getRoute() {
        int[] dict = new int[N + 1];
        Arrays.fill(dict, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(C);
        dict[C] = 0;
        int d = 1;
        w:
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int s = q.poll();
                if (s == B) break w;
                for (Edge e : edges[s]) {
                    if (dict[e.e] != Integer.MAX_VALUE) continue;
                    dict[e.e] = d;
                    q.offer(e.e);
                }
            }
            d++;
        }

        route = new boolean[N + 1];
        int curr = B;
        route[B] = true;
        while (curr != C) {
            int next = 0;
            for (Edge e : edges[curr]) {
                if (dict[curr] - 1 != dict[e.e]) continue;
                if (degree[next] > degree[e.e]) continue;
                next = degree[next] == degree[e.e] ? Math.max(next, e.e) : e.e;
            }
            curr = next;
            route[curr] = true;
        }
    }

    private static int find() {
        long[] memo = new long[N + 1];
        Arrays.fill(memo, Long.MAX_VALUE);
        PriorityQueue<Toka> pq = new PriorityQueue<>();
        pq.offer(new Toka(A, 0));
        memo[A] = 0;
        while (!pq.isEmpty()) {
            Toka s = pq.poll();
            if (route[s.e]) return s.e;
            if (memo[s.e] < s.w) continue;
            for (Edge e : edges[s.e]) {
                long w = memo[s.e] + e.w;
                if (memo[e.e] <= w) continue;
                memo[e.e] = w;
                pq.offer(new Toka(e.e, w));
            }
        }
        return 0;
    }

    private static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    private static class Toka implements Comparable<Toka> {
        int e;
        long w;

        public Toka(int e, long w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Toka o) {
            return this.w != o.w ? Long.compare(this.w, o.w) : Integer.compare(this.e, o.e);
        }
    }
}