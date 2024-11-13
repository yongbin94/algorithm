import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B;
    static List<Edge>[] E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        E = new ArrayList[N + 1];
        Arrays.setAll(E, e -> new ArrayList<>());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            E[a].add(new Edge(b, w, 0, 0));
            E[b].add(new Edge(a, w, 0, 0));
        }
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(A, 0, 0, 1));
        pq.offer(new Edge(B, 0, 0, 2));
        int[] V = new int[N + 1];
        int[] U = new int[N + 1];
        Arrays.fill(V, -1);
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (U[s.e] != 0) {
                System.out.println(V[s.e] + s.t - s.w);
                return;
            }
            V[s.e] = s.t;
            U[s.e] = s.u;

            for (Edge e : E[s.e]) {
                if (V[e.e] >= 0) continue;
                pq.offer(new Edge(e.e, e.w, s.t + e.w, s.u));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e, w, t, u;

        public Edge(int e, int w, int t, int u) {
            this.e = e;
            this.w = w;
            this.t = t;
            this.u = u;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}