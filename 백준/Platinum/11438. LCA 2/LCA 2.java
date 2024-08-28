import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] parents;
    static int[] levels;
    static List<Integer>[] lists;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        lists = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        levels = new int[N + 1];
        L = (int) Math.ceil(Math.log(N) / Math.log(2));

        parents = new int[N + 1][L + 1];
        for (int n = 1; n <= N; n++)
            lists[n] = new ArrayList<>();

        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }
        fillLevels();
        fillParents();


        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    private static void fillLevels() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        levels[1] = 0;
        visited[1] = true;

        while (!q.isEmpty()) {
            int s = q.poll();
            for (int e : lists[s]) {
                if (visited[e])
                    continue;
                visited[e] = true;
                levels[e] = levels[s] + 1;
                parents[e][0] = s;
                q.offer(e);
            }
        }
    }

    private static void fillParents() {
        for (int j = 1; j <= L; j++) {
            for (int i = 1; i <= N; i++) {
                parents[i][j] = parents[parents[i][j - 1]][j - 1];
            }
        }
    }

    private static int lca(int a, int b) {
        if (levels[a] > levels[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int j = L; j >= 0; j--)
            if (levels[b] - levels[a] >= (1 << j))
                b = parents[b][j];

        if (a == b)
            return a;

        for (int j = L; j >= 0; j--) {
            if (parents[a][j] != parents[b][j]) {
                a = parents[a][j];
                b = parents[b][j];
            }
        }

        return parents[a][0];
    }
}