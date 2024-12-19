import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer, score;
    static int[] A;
    static int[][] map, org;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        org = new int[N + 2][N + 2];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m <= N; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                org[n][m] = map[n][m];
            }
        }
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        recur(0);
        System.out.println(answer);
    }

    private static void recur(int cnt) {
        answer = Math.max(answer, score);
        if (cnt == K)
            return;
        int[][] tmp;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int v = map[r][c];
                if (v == 0)
                    continue;
                tmp = save(r, c, A[cnt]);
                int sc = 0;
                if (map[r][c] == 0) {
                    sc = org[r][c];
                    score += sc;
                    org[r][c] = 0;
                }
                recur(cnt + 1);
                if (org[r][c] == 0)
                    score -= sc;
                load(r, c, tmp);
                break;
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0};

    private static int[][] save(int r, int c, int a) {
        int[][] result = new int[2][5];
        for (int d = 0; d < 5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            result[0][d] = map[nr][nc];
            result[1][d] = org[nr][nc];
        }
        map[r][c] = Math.max(0, map[r][c] - a);
        if (org[r][c] >= 10) {
            map[r][c] = 0;
            return result;
        }
        if(map[r][c] != 0)
            return result;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (map[nr][nc] == 0) {
                map[nr][nc] = org[r][c] / 4;
                org[nr][nc] = map[nr][nc];
            }
        }
        return result;
    }

    private static void load(int r, int c, int[][] tmp) {
        for (int d = 0; d < 5; d++) {
            map[r + dr[d]][c + dc[d]] = tmp[0][d];
            org[r + dr[d]][c + dc[d]] = tmp[1][d];
        }
    }
}