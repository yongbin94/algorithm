import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
    static ArrayList<Integer>[] edges;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            edges = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
            color = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges[u].add(v);
                edges[v].add(u);
            }

            boolean ok = true;
            for (int i = 1; i <= N; i++) {
                if (color[i] == 0) {
                    if (!check(i)) {
                        ok = false;
                        break;
                    }
                }
            }
            sb.append(ok ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }

    private static boolean check(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        color[start] = 1;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : edges[u]) {
                if (color[v] == 0) {
                    color[v] = 3 - color[u];
                    q.offer(v);
                } else if (color[v] == color[u]) {
                    return false;
                }
            }
        }
        return true;
    }
}