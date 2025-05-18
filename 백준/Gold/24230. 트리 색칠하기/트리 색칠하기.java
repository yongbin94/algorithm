import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[] C;
    static List<Integer>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = new int[N + 1];
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            C[n] = Integer.parseInt(st.nextToken());
        }
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        answer = 0;
        dfs(1, 0);
        System.out.println(answer);
    }

    private static void dfs(int u, int color) {
        visited[u] = true;
        if (color != C[u]) {
            color = C[u];
            answer++;
        }
        for (int v : edges[u]) {
            if (visited[v]) continue;
            dfs(v, color);
        }
    }
}