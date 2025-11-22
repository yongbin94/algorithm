import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edges;
    static boolean[] visited;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        visited = new boolean[N + 1];
        visited[R] = true;
        A = new int[N + 1];
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        dfs(R);
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            sb.append(A[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int n) {
        int res = 0;
        for (int v : edges[n]) {
            if (visited[v]) continue;
            visited[v] = true;
            res += dfs(v);
        }
        return A[n] = ++res;
    }
}