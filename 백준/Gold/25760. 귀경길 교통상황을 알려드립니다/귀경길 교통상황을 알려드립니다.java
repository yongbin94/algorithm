import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edges;
    static boolean[] A;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N + 1];
        cnt = new int[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        A = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = st.nextToken().charAt(0) == '1';
        }
        bfs();
        cnt[0] = A[1] ? 1 : 0;
        int answer = 0;
        int sum = 0;
        for (int i = N; i >= 0; i--) {
            sum += cnt[i];
            if (sum > 0)
                answer = Math.max(answer, i + sum);
        }
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        q.offer(1);
        int depth = 1;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int u = q.poll();
                for (int v : edges[u]) {
                    if (visited[v]) continue;
                    visited[v] = true;
                    q.offer(v);
                    if (A[v]) cnt[depth]++;
                }
            }
            depth++;
        }
    }
}
