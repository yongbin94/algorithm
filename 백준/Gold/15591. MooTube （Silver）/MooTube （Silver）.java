import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(solution(k, v)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(int k, int n) {
        int res = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        visited[n] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge v : edges[u]) {
                if(visited[v.n] || v.w < k) continue;
                visited[v.n] = true;
                q.offer(v.n);
                res++;
            }
        }
        return res;
    }

    private static class Edge {
        int n, w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}