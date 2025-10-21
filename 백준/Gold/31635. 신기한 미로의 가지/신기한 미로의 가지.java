import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> list;
    static boolean[] visited;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        visited[1] = true;
        list = new ArrayList<>();
        dfs(1, 0);
        list.sort(((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        StringBuilder sb = new StringBuilder();
        sb.append("answer\n");
        for (int[] a : list) {
            sb.append(String.format("%d %d\n", a[0], a[1]));
        }
        System.out.println(sb);
        System.out.flush();
        System.exit(0);
    }

    private static void dfs(int u, int p) throws IOException {
        while (true) {
            System.out.println("maze");
            System.out.flush();
            int v = Integer.parseInt(br.readLine());
            if (visited[v]) {
                if (v == p || p == 0) return;
                System.out.printf("gaji %d\n", u);
                System.out.flush();
                br.readLine();
                System.out.printf("gaji %d\n", p);
                System.out.flush();
                br.readLine();
                return;
            }
            visited[v] = true;
            list.add(u > v ? new int[]{v, u} : new int[]{u, v});
            dfs(v, u);
        }
    }
}
