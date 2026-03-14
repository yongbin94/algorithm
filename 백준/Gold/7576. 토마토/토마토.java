import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[M][N];

        for(int m = 0; m < M; m++) {
            int idx = 0;
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens())
                A[m][idx++] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs());
    }

    static class Pos {
        int y;
        int x;
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        for(int y = 0; y < M; y++) {
            for(int x = 0; x < N; x++) {
                if(A[y][x] == 1)
                    q.offer(new Pos(y, x));
            }
        }
        while(!q.isEmpty()) {
            Pos pos = q.poll();
            for(int d = 0; d < 4; d++) {
                int ny = pos.y + dy[d];
                int nx = pos.x + dx[d];
                if(ny < 0 || nx < 0 || ny >= M || nx >= N)
                    continue;
                if(A[ny][nx] == 0) {
                    A[ny][nx] = A[pos.y][pos.x] + 1;
                    q.offer(new Pos(ny, nx));
                }
            }
        }
        int result = 0;
        for(int y = 0; y < M; y++) {
            for(int x = 0; x < N; x++) {
                if(A[y][x] == 0)
                    return -1;
                if(result < A[y][x])
                    result = A[y][x];
            }
        }
        return result - 1;
    }
}