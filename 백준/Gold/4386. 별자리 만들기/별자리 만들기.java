import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Star[] S = new Star[N];
        for (int n = 0; n < N; n++) {
            S[n] = new Star(new StringTokenizer(br.readLine()));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        boolean[] visited = new boolean[N];
        double[] memo = new double[N];
        Arrays.fill(memo, Double.MAX_VALUE);
        memo[0] = 0;
        double answer = 0;
        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            if (visited[u.n]) continue;
            visited[u.n] = true;
            answer += u.w;
            for (int v = 0; v < N; v++) {
                if (visited[v]) continue;
                double nw = Math.sqrt(Math.pow(S[u.n].r - S[v].r, 2) + Math.pow(S[u.n].c - S[v].c, 2));
                if (nw >= memo[v]) continue;
                memo[v] = nw;
                pq.offer(new Edge(v, nw));
            }
        }
        System.out.printf("%.2f", answer);
    }

    private static class Star {
        double r, c;

        public Star(StringTokenizer st) {
            r = Double.parseDouble(st.nextToken());
            c = Double.parseDouble(st.nextToken());
        }
    }

    private static class Edge implements Comparable<Edge> {
        int n;
        double w;

        public Edge(int n, double w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}