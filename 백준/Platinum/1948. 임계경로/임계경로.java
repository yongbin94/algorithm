import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static int[] in, memo;
    static boolean[] visited;
    static List<Edge>[] edges;
    static List<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new ArrayList[N];
        lists = new ArrayList[N];
        in = new int[N];
        memo = new int[N];
        visited = new boolean[N];
        for (int n = 0; n < N; n++) {
            edges[n] = new ArrayList();
            lists[n] = new ArrayList();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[s].add(new Edge(e, w));
            in[e]++;
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        E = Integer.parseInt(st.nextToken()) - 1;
        solution();
        checkPath();
    }

    private static void solution() {
        Queue<Edge> q = new ArrayDeque<>();
        q.add(new Edge(S, 0));
        while (!q.isEmpty()) {
            Edge s = q.poll();
            for (Edge e : edges[s.e]) {
                int w = s.w + e.w;
                if (memo[e.e] < w) {
                    lists[e.e].clear();
                    memo[e.e] = w;
                }
                if (memo[e.e] <= w)
                    lists[e.e].add(s.e);
                if (--in[e.e] == 0)
                    q.offer(new Edge(e.e, memo[e.e]));
            }
        }
        System.out.println(memo[N - 1]);
    }

    private static void checkPath() {
        int res = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(E);
        while (!q.isEmpty()) {
            int n = q.poll();
            for(int i : lists[n]){
                res++;
                if(visited[i])
                    continue;
                visited[i] = true;
                q.offer(i);
            }
        }
        System.out.println(res);
    }

    private static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}