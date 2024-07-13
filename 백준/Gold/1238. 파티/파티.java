import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<Edge>[] depart, arrive;
    static int[] D, A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        depart = new ArrayList[N];
        arrive = new ArrayList[N];
        for (int n = 0; n < N; n++) {
            depart[n] = new ArrayList<>();
            arrive[n] = new ArrayList<>();
        }
        D = new int[N];
        A = new int[N];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            depart[e].add(new Edge(s, w));
            arrive[s].add(new Edge(e, w));
        }

        solution(depart, D);
        solution(arrive, A);
        int answer = 0;
        for (int n = 0; n < N; n++)
            answer = Math.max(answer, D[n] + A[n]);
        System.out.println(answer);
    }

    private static void solution(List<Edge>[] list, int[] memo) {
        Arrays.fill(memo, 100_000_000);
        memo[X] = 0;
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(X, 0));
        while (!q.isEmpty()) {
            Edge s = q.poll();
            if (memo[s.e] < s.w)
                continue;
            for (Edge e : list[s.e]) {
                if (memo[e.e] <= s.w + e.w)
                    continue;
                memo[e.e] = s.w + e.w;
                q.offer(new Edge(e.e, memo[e.e]));
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
