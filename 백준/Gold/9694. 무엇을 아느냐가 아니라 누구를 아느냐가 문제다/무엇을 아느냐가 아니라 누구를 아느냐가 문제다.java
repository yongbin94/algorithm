import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static List<Edge>[] edges;
    static int[] memo;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1, T = Integer.parseInt(br.readLine()); tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edges = new ArrayList[M];
            Arrays.setAll(edges, v -> new ArrayList<>());
            memo = new int[M];
            Arrays.fill(memo, Integer.MAX_VALUE);
            prev = new int[M];
            prev[0] = -1;
            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[s].add(new Edge(e, w));
                edges[e].add(new Edge(s, w));
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(0, 0));
            memo[0] = 0;
            while (!pq.isEmpty()) {
                Edge s = pq.poll();
                if (memo[s.e] < s.w) continue;
                for (Edge e : edges[s.e]) {
                    if (memo[e.e] <= s.w + e.w) continue;
                    memo[e.e] = s.w + e.w;
                    prev[e.e] = s.e;
                    pq.offer(new Edge(e.e, memo[e.e]));
                }
            }
            sb.append(String.format("Case #%d:%s\n", tc, getRoute()));
        }
        System.out.println(sb);
    }

    private static String getRoute() {
        int i = M - 1;
        if (memo[i] == Integer.MAX_VALUE) return " -1";
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (i != -1) {
            stack.push(i);
            i = prev[i];
        }
        while(!stack.isEmpty()) {
            sb.append(" ").append(stack.pop());
        }
        return sb.toString();
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