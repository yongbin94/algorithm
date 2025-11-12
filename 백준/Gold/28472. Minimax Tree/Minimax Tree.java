import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edges;
    static int[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        A = new int[N + 1];
        Arrays.fill(A, -1);
        visited = new boolean[N + 1];
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        int L = Integer.parseInt(br.readLine());
        while (L-- > 0) {
            st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        dfs(R, true);
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            sb.append(A[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int n, boolean isMax) {
        visited[n] = true;
        if (A[n] != -1) return A[n];
        int res = isMax ? 0 : Integer.MAX_VALUE;
        for (int v : edges[n]) {
            if (visited[v]) continue;
            res = isMax ? Math.max(res, dfs(v, false)) : Math.min(res, dfs(v, true));
        }
        A[n] = res;
        return res;
    }
}