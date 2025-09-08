import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] prev;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        prev = new int[120];
        visited = new boolean[120];
        bfs();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            int[] res = new int[5];
            res[0] += N / 60;
            N %= 60;
            while (N != 0) {
                res[prev[N]]++;
                N -= dt[prev[N]];
            }
            for (int n = 0; n < 5; n++) {
                sb.append(res[n]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dt = {60, 10, -10, 1, -1};

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        prev[0] = -1;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int d = 4; d >= 0; d--) {
                int next = curr + dt[d];
                if (next >= 120 || next < 0 || visited[next]) continue;
                visited[next] = true;
                q.offer(next);
                prev[next] = d;
            }
        }
    }
}