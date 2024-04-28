import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][];
        boolean[][] visited = new boolean[N][N];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0,  0));
        visited[0][0] = true;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if(p.r == N - 1 && p.c == N-1){
                System.out.println(p.k);
                return;
            }
            for(int d=  0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if(!isIn(nr,nc) || visited[nr][nc])
                    continue;
                visited[nr][nc] =true;
                pq.offer(new Pos(nr,nc, p.k + (map[nr][nc] == '1' ? 0 : 1)));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c, k;

        public Pos(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }

        @Override
        public int compareTo(Pos o) {
            return this.k - o.k;
        }
    }
}
