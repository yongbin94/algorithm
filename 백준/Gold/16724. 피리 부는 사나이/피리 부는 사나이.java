import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static char[][] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        input = new char[N][];
        for (int i = 0; i < N; i++)
            input[i] = br.readLine().toCharArray();
        int idx = 1;
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (recur(r, c, idx) == idx)
                    idx++;
        
        System.out.println(idx - 1);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int recur(int r, int c, int idx) {
        map[r][c] = idx;
        int d = "URDL".indexOf(input[r][c]);
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (!isIn(nr, nc))
            return idx;
        if (map[nr][nc] != 0) {
            map[r][c] = map[nr][nc];
            return map[nr][nc];
        }
        map[r][c] = recur(nr, nc, idx);
        return map[r][c];
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}