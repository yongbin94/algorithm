import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, S, G, H;
    static List<Edge>[] list;
    static int[] memo, A, B;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int L = 0;

            A = new int[N + 1];
            B = new int[N + 1];
            list = new ArrayList[N + 1];
            for (int n = 1; n <= N; n++)
                list[n] = new ArrayList<>();

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list[a].add(new Edge(b, d));
                list[b].add(new Edge(a, d));
                if (a == H && b == G || a == G && b == H)
                    L = d;
            }
            solution(A, new Edge(S, 0));
            solution(B, new Edge(A[G] < A[H] ? H : G, L + Math.min(A[G], A[H])));

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            while (K-- > 0)
                pq.offer(Integer.parseInt(br.readLine()));
            while (!pq.isEmpty()) {
                int v = pq.poll();
                if (A[v] == B[v] && A[v] != 1_000_000_000)
                    sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solution(int[] memo, Edge start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(memo, 1_000_000_000);
        pq.offer(start);
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[s.e] < s.w)
                continue;
            memo[s.e] = s.w;
            for (Edge e : list[s.e]) {
                if (memo[e.e] <= s.w + e.w)
                    continue;
                memo[e.e] = s.w + e.w;
                pq.offer(new Edge(e.e, memo[e.e]));
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