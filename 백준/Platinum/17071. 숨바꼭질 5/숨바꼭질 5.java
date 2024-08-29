import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[2][500001];
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[1][N] = true;
        while (true) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int n = q.poll();
                if (n >= 1 && !visited[cnt % 2][n - 1]) {
                    visited[cnt % 2][n - 1] = true;
                    q.offer(n - 1);
                }
                if (n < 500000 && !visited[cnt % 2][n + 1]) {
                    visited[cnt % 2][n + 1] = true;
                    q.offer(n + 1);
                }
                if (n * 2 < 500001 && !visited[cnt % 2][n * 2]) {
                    visited[cnt % 2][n * 2] = true;
                    q.offer(n * 2);
                }
            }
            M += cnt;
            if(M > 500000) {
                System.out.println(-1);
                return;
            }
            if (visited[++cnt % 2][M]) {
                System.out.println(cnt - 1);
                return;
            }
         }
    }
}