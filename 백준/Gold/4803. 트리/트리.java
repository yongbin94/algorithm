import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean isTree;
    static boolean[] visited;
    static List<Integer>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;

            A = new ArrayList[N];
            for (int i = 0; i < N; i++)
                A[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                A[u].add(v);
                A[v].add(u);
            }

            visited = new boolean[N];
            int treeCount = 0;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    isTree = true;
                    if (dfs(i, -1))
                        treeCount++;
                }
            }

            sb.append("Case ").append(T++);
            if (treeCount == 0)
                sb.append(": No trees.\n");
            else if (treeCount == 1)
                sb.append(": There is one tree.\n");
            else
                sb.append(": A forest of ").append(treeCount).append(" trees.\n");
        }
        System.out.println(sb);
    }

    static boolean dfs(int node, int parent) {
        visited[node] = true;

        for (int neighbor : A[node]) {
            if (!visited[neighbor]) {
                if (!dfs(neighbor, node))
                    isTree = false;
            } else if (neighbor != parent) {
                isTree = false;
            }
        }
        return isTree;
    }
}