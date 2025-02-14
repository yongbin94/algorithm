import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static List<Edge> edges;
    static Set<Long> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        long init = 0;
        for (int i = 0; i < N; i++) {
            init <<= 4;
            init += A[i];
        }
        Arrays.sort(A);
        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer <<= 4;
            answer += A[i];
        }

        edges = new ArrayList<>();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, w));
        }
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(init, 0));
        visited = new HashSet<>();
        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (info.v == answer) {
                System.out.println(info.w);
                return;
            }

            if (visited.contains(info.v))
                continue;
            visited.add(info.v);

            for (Edge edge : edges) {
                long v = move(info.v, edge);
                if (visited.contains(v))
                    continue;
                pq.offer(new Info(v, info.w + edge.w));
            }
        }
        System.out.println(-1);
    }

    private static long move(long v, Edge edge) {
        int mask = 15;

        int a = (int) ((v >> (4 * (N - 1 - edge.s))) & mask);
        int b = (int) ((v >> (4 * (N - 1 - edge.e))) & mask);

        v &= ~((long) mask << (4 * (N - 1 - edge.s)));
        v &= ~((long) mask << (4 * (N - 1 - edge.e)));

        v |= ((long) b << (4 * (N - 1 - edge.s)));
        v |= ((long) a << (4 * (N - 1 - edge.e)));

        return v;
    }

    private static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    private static class Info implements Comparable<Info> {
        long v;
        int w;

        public Info(long v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Info o) {
            return this.w - o.w;
        }
    }
}