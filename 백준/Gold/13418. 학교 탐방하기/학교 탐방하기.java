import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for (int n = 0; n <= N; n++)
            edges[n] = new ArrayList<>();
        while (M-- >= 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w == 1 ? 0 : 1));
            edges[b].add(new Edge(a, w == 1 ? 0 : 1));
        }

        int max = solution((o1, o2) -> o2.w - o1.w);
        int min = solution((o1, o2) -> o1.w - o2.w);
        System.out.println(max * max - min * min);
    }

    private static int solution(Comparator<Edge> edgeComparator) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(edgeComparator);
        pq.offer(new Edge(0, 0));
        visited = new boolean[N + 1];
        int answer = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            answer += s.w;
            for(Edge e : edges[s.e]) {
                if(visited[e.e])
                    continue;
                pq.offer(e);
            }
        }
        return answer;
    }

    private static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}