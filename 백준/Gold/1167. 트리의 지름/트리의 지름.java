import java.io.*;
import java.util.*;

public class Main {
    static int N, max, index;
    static List<Edge>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        index = 0;
        list = new ArrayList[N];
        for (int i = 0; i < N; i++)
            list[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1)
                    break;
                int c = Integer.parseInt(st.nextToken());
                list[a - 1].add(new Edge(b - 1, c));
            }
        }
        bfs();
        bfs();
        System.out.println(max);
    }

    private static void bfs() {
        max = 0;
        visited = new boolean[N];
        Queue<Edge> q = new ArrayDeque<>();
        visited[index] = true;
        q.offer(new Edge(index,0));
        while(!q.isEmpty()) {
            Edge s = q.poll();
            for(Edge e : list[s.e]) {
                if(visited[e.e])
                    continue;
                visited[e.e] = true;
                if(max < s.w + e.w) {
                    max = s.w + e.w;
                    index = e.e;
                }
                q.offer(new Edge(e.e, s.w + e.w));
            }
        }
    }

    private static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

    }
}