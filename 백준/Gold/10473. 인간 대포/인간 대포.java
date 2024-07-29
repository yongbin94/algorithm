import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Pos S;
    static Pos[] A;
    static double[] memo;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = new Pos(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Pos E = new Pos(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        N = Integer.parseInt(br.readLine());
        A = new Pos[N + 1];
        memo = new double[N + 1];
        Arrays.fill(memo, 100_000_000_000_000L);
        visited = new boolean[N + 1];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            A[n] = new Pos(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }
        A[N] = E;
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int n = 0; n <= N; n++) {
            memo[n] = S.getDistance(A[n]) / 5;
            pq.offer(new Edge(n, memo[n]));
        }
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == N) {
                System.out.println(s.w);
                return;
            }
            if (visited[s.e])
                continue;
            visited[s.e] = true;
            for (int n = 0; n <= N; n++) {
                if (s.e == n)
                    continue;
                double dist = A[s.e].getDistance(A[n]);
                double time = Math.min(2 + Math.abs(dist - 50) / 5, dist / 5);
                if (memo[n] <= s.w + time)
                    continue;
                memo[n] = s.w + time;
                pq.offer(new Edge(n, memo[n]));
            }
        }

    }

    private static class Pos {
        double x, y;

        public Pos(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Pos pos) {
            return Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2));
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e;
        double w;

        public Edge(int e, double w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}