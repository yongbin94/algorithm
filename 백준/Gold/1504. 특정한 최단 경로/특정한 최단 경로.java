import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] min;
    static List<Edge>[] list;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        min = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, w));
            list[b].add(new Edge(a, w));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        memo = new int[3][N + 1];
        dijkstra(memo[0], 1);
        dijkstra(memo[1], A);
        dijkstra(memo[2], N);
        int answer = Integer.MAX_VALUE;
        if (!(memo[1][B] == Integer.MAX_VALUE)) {
            if (!(memo[0][A] == Integer.MAX_VALUE) && !(memo[2][B] == Integer.MAX_VALUE))
                answer = Math.min(answer, memo[0][A] + memo[1][B] + memo[2][B]);
            if (!(memo[0][B] == Integer.MAX_VALUE) && !(memo[2][A] == Integer.MAX_VALUE))
                answer = Math.min(answer, memo[0][B] + memo[1][B] + memo[2][A]);
            if (!(memo[0][A] == Integer.MAX_VALUE) && !(memo[2][A] == Integer.MAX_VALUE))
                answer = Math.min(answer, memo[0][A] + memo[1][B] + memo[1][B] + memo[2][A]);
            if (!(memo[0][B] == Integer.MAX_VALUE) && !(memo[2][B] == Integer.MAX_VALUE))
                answer = Math.min(answer, memo[0][B] + memo[1][B] + memo[1][B] + memo[2][B]);
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dijkstra(int[] A, int i) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(A, Integer.MAX_VALUE);
        pq.offer(new Edge(i, 0));
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (A[s.e] <= s.w)
                continue;
            A[s.e] = s.w;
            for (Edge e : list[s.e]) {
                int w = A[s.e] + e.w;
                if (A[e.e] <= w)
                    continue;
                pq.offer(new Edge(e.e, w));
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
            return this.w - o.w;
        }
    }
}
