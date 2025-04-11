import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<Integer>[] edges;
    static boolean[] visited;
    static HashMap<String, Set<Integer>> groups;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        groups = new HashMap<>();
        int W = Integer.parseInt(br.readLine());
        while (W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            groups.computeIfAbsent(st.nextToken(), v -> new HashSet<>()).add(u);
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        for (Set<Integer> group : groups.values()) {
            if (!solve(group)) continue;
            System.out.println("POWERFUL CODING JungHwan");
            return;
        }
        System.out.println("so sad");
    }

    private static boolean solve(Set<Integer> group) {
        visited = new boolean[N + 1];
        for (int idx : group) {
            if (visited[idx]) continue;
            if (bfs(idx, group))
                return true;
        }
        return false;
    }

    private static boolean bfs(int idx, Set<Integer> group) {
        visited[idx] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(idx);
        int depth = 0;
        while (!q.isEmpty()) {
            if (++depth > K) return false;
            for (int i = 0, size = q.size(); i < size; i++) {
                int s = q.poll();
                for (int e : edges[s]) {
                    if (visited[e]) continue;
                    visited[e] = true;
                    if (!group.contains(e)) q.offer(e);
                    else return true;
                }
            }
        }
        return false;
    }
}