import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;
    static int[] parent;
    static Map<Integer, ArrayList<Integer>> groups;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new boolean[N][N];
        parent = new int[N];
        for (int n = 0; n < N; n++) {
            parent[n] = n;
        }
        for (int a = 0; a < N; a++) {
            st = new StringTokenizer(br.readLine());
            for (int b = 0; b < N; b++) {
                map[a][b] = st.nextToken().charAt(0) == '0';
                if (!map[a][b] || a > b) continue;
                union(a, b);
            }
        }
        groups = new HashMap<>();
        for (int n = 0; n < N; n++) {
            int root = find(n);
            groups.putIfAbsent(root, new ArrayList<>());
            groups.get(root).add(n);
        }
        if (!check()) {
            System.out.println(0);
            return;
        }
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append("\n");
        for (ArrayList<Integer> group : groups.values()) {
            for (int n : group) {
                sb.append(n + 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean check() {
        for (ArrayList<Integer> group : groups.values()) {
            if (group.size() == 1) return false;
            for (int u : group) {
                for (int v : group) {
                    if (!map[u][v]) return false;
                }
            }
        }
        return true;
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        parent[rootB] = rootA;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}