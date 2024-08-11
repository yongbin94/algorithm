import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] in;
    static List<Edge>[] list;
    static Map<Integer, Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        in = new int[N + 1];
        list = new List[N + 1];
        map = new Map[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            map[i] = new HashMap<>();
        }
        map[N] = new TreeMap<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            list[Y].add(new Edge(X, K));
            in[X]++;
        }
        solution();
        StringBuilder sb = new StringBuilder();
        for(int key : map[N].keySet()) {
            sb.append(key).append(" ").append(map[N].get(key)).append("\n");
        }
        System.out.println(sb);
    }

    private static void solution() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++)
            if (in[i] == 0)
                q.offer(i);
        for(int a = 0, size = q.size(); a < size; a++) {
            int i = q.poll();
            for(Edge e : list[i]) {
                map[e.x].put(i, map[e.x].getOrDefault(e.x, 0) + e.k);
                if(--in[e.x] == 0)
                    q.offer(e.x);
            }
        }
        while(!q.isEmpty()) {
            int i = q.poll();
            for(Edge e : list[i]) {
                for(int key : map[i].keySet())
                    map[e.x].put(key, map[e.x].getOrDefault(key, 0) + map[i].get(key) * e.k);
                if(--in[e.x] == 0)
                    q.offer(e.x);
            }
        }
    }

    private static class Edge {
        int x, k;

        public Edge(int x, int k) {
            this.x = x;
            this.k = k;
        }
    }
}