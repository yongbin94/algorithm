import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Point[] A;
    static List<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            A = new Point[N + 2];
            visited = new boolean[N + 2];
            list = new ArrayList[N + 2];
            for (int n = 0; n < N + 2; n++)
                list[n] = new ArrayList<>();
            for (int n = 0; n < N + 2; n++) {
                st = new StringTokenizer(br.readLine());
                A[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                for (int i = 0; i < n; i++) {
                    if (Math.abs(A[n].x - A[i].x) + Math.abs(A[n].y - A[i].y) <= 1000) {
                        list[i].add(n);
                        list[n].add(i);
                    }
                }
            }
            sb.append(bfs() ? "happy" : "sad").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == N + 1)
                return true;
            for (int i : list[v]) {
                if (visited[i])
                    continue;
                visited[i] = true;
                q.offer(i);
            }
        }
        return false;
    }
}