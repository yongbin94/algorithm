import java.io.*;
import java.util.*;

public class Main {
    static final int N = 100001;
    static int count, start;
    static List<Edge>[] edges;
    static boolean[] visited, visited2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        visited = new boolean[N];
        edges = new ArrayList[N];
        String input = null;
        int a = 0, b;
        edges[0] = new ArrayList<>();
        while ((input = br.readLine()) != null) {
            if(input.isEmpty()) break;
            st = new StringTokenizer(input);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (edges[a] == null) {
                edges[a] = new ArrayList<>();
                count++;
            }
            if (edges[b] == null) {
                edges[b] = new ArrayList<>();
                count++;
            }
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        
        visited2 = visited.clone();
        solution(visited, a);
        System.out.println(solution(visited2, start));
    }

    private static int solution(boolean[] V, int a) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(a, 0));
        int cnt = count;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (V[s.e])
                continue;
            V[s.e] = true;
            if (--cnt == 0) {
                start = s.e;
                return s.w;
            }
            for (Edge e : edges[s.e]) {
                if (V[e.e])
                    continue;
                pq.offer(new Edge(e.e, s.w + e.w));
            }
        }
        return 0;
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