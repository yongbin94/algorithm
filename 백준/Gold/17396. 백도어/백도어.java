import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] ward;
    static List<Edge>[] list;
    static long[] max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ward = new boolean[N];
        list = new ArrayList[N];
        max = new long[N];
        Arrays.fill(max, 1_000_000_000_000_000L);
        max[0] = 0;
        for (int n = 0; n < N; n++)
            list[n] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N - 1; n++)
            ward[n] = st.nextToken().equals("1");
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (ward[a] || ward[b])
                continue;
            list[a].add(new Edge(b, w));
            list[b].add(new Edge(a, w));
        }
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == N - 1) {
                System.out.println(s.w);
                return;
            }
            if (max[s.e] < s.w)
                continue;
            for (Edge e : list[s.e]) {
                if (max[e.e] <= s.w + e.w)
                    continue;
                max[e.e] = s.w + e.w;
                pq.offer(new Edge(e.e, max[e.e]));
            }
        }
        System.out.println(-1);
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