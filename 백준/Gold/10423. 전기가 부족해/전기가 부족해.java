import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K ,answer;
    static List<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        visited = new boolean[N];
        for (int n = 0; n < N; n++)
            graph[n] = new ArrayList<>();
        StringTokenizer tmp = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int k = 0; k < K; k++) {
            int s = Integer.parseInt(tmp.nextToken()) - 1;
            visited[s] = true;
            for (Edge e : graph[s])
                pq.offer(e);
        }

        while(!pq.isEmpty()) {
            Edge s = pq.poll();
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            answer += s.w;
            for(Edge e : graph[s.e]) {
                if(visited[e.e])
                    continue;
                pq.offer(e);
            }
        }
        System.out.println(answer);
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