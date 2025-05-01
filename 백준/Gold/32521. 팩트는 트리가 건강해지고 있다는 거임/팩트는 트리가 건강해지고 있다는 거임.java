import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;
    static boolean[] healthy;
    static List<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        healthy = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            healthy[n] = st.nextToken().charAt(0) == '0';
        }
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        dfs(1, 0);
        System.out.println(answer);
    }

    private static int dfs(int u, int parent) {
        int res = healthy[u] ? 0 : 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int v : edges[u]) {
            if (v == parent) continue;
            pq.offer(dfs(v, u));
        }
        while(!pq.isEmpty()) {
            if(res + pq.peek() > K) {
                answer += pq.size();
                break;
            }
            res += pq.poll();
        }
        return res;
    }
}