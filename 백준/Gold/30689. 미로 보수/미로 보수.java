import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] cost;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        cost = new int[N][M];
        visited = new int[N][M];
        for (int r = 0; r < N; r++)
            map[r] = br.readLine().toCharArray();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++)
                cost[r][c] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c] == 0)
                    answer += solution(r, c);
            }
        }
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};


    private static int solution(int r, int c) {
        int n = r * M + c + 1;
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        visited[r][c] = n;
        int nr = r + dr[getD(map[r][c])];
        int nc = c + dc[getD(map[r][c])];
        while (isIn(nr, nc) && (visited[nr][nc] == 0 || visited[nr][nc] == n)) {
            if(flag && r == nr && c == nc) {
                return min;
            }
            if (visited[nr][nc] == n) {
                if (!flag) {
                    flag = true;
                    r = nr;
                    c = nc;
                }
                min = Math.min(min, cost[nr][nc]);
            }
            visited[nr][nc] = n;
            char d = map[nr][nc];
            nr += dr[getD(d)];
            nc += dc[getD(d)];
        }
        return 0;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static int getD(char ch) {
        return ch == 'R' ? 0 : ch == 'D' ? 1 : ch == 'L' ? 2 : 3;
    }
}