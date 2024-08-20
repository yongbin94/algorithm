import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] memo;
    static List<Edge>[] list;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        memo = new int[N + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(a, b, w));
            list[b].add(new Edge(b, a, w));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : list[1]) {
            if (memo[e.e] < e.w)
                continue;
            memo[e.e] = e.w;
            pq.offer(new Edge(1, e.e, memo[e.e]));
        }
        memo[1] = 0;
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        set = new HashSet<>();
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[s.e] < s.w || set.contains(s.e))
                continue;
            sb.append(s.s).append(" ").append(s.e).append("\n");
            cnt++;
            set.add(s.e);
            if (set.size() == N - 1) {
                System.out.println(cnt);
                System.out.println(sb);
                return;
            }
            for (Edge e : list[s.e]) {
                if (memo[e.e] < s.w + e.w)
                    continue;
                memo[e.e] = s.w + e.w;
                pq.offer(new Edge(s.e, e.e, memo[e.e]));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}