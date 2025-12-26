import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int[] memo = new int[N];
        Arrays.fill(memo, 1_000_000_000);
        List<Edge>[] edges = new ArrayList[N];
        Arrays.setAll(edges, v -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(Y, 0));
        memo[Y] = 0;
        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            if (u.w > memo[u.n]) continue;
            if (u.w * 2 > X) {
                System.out.println(-1);
                return;
            }
            for (Edge v : edges[u.n]) {
                int nw = u.w + v.w;
                if (memo[v.n] <= nw) continue;
                memo[v.n] = nw;
                pq.offer(new Edge(v.n, nw));
            }
        }
        Arrays.sort(memo);
        int answer = 1, x = 0;
        for (int v : memo) {
            if (x + v * 2 > X) {
                x = 0;
                answer++;
            }
            x += v * 2;
        }
        System.out.println(answer);
    }

    private static class Edge implements Comparable<Edge> {
        int n, w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}