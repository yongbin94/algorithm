import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            parent = new int[R * C];
            for (int i = 0; i < R * C; i++)
                parent[i] = i;
            pq = new PriorityQueue<>();
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C - 1; c++) {
                    pq.offer(new Edge(r * C + c, r * C + c + 1, Integer.parseInt(st.nextToken())));
                }
            }
            for (int r = 0; r < R - 1; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    pq.offer((new Edge(r * C + c, (r + 1) * C + c, Integer.parseInt(st.nextToken()))));
                }
            }
            int answer = 0;
            int count = 0;
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                if (union(e.s, e.e)) {
                    answer += e.w;
                    if (++count == R * C - 1) {
                        break;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return false;
        parent[rootB] = rootA;
        return true;
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
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