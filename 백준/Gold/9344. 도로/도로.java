import java.io.*;
import java.util.*;

public class Main {
    static int N, M, p, q;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            while (M-- > 0) {
                pq.offer(new Edge(new StringTokenizer(br.readLine())));
            }
            parents = new int[N + 1];
            for (int n = 1; n <= N; n++) {
                parents[n] = n;
            }
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (union(edge.u, edge.v)) {
                    if (edge.check()) {
                        sb.append("YES\n");
                        break;
                    }
                    if (--N > 1) continue;
                    sb.append("NO\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }


    private static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(StringTokenizer st) {
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
        }

        public boolean check() {
            return (u == p && v == q) || (u == q && v == p);
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}