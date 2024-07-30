import java.io.*;
import java.util.*;

public class Main {
    static int N, M, A;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                recur(r, c);
        System.out.println(A);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static boolean recur(int r, int c) {
        if(map[r][c] == 'V' || map[r][c] == 'X') {
            map[r][c] = 'X';
            return false;
        }
        if(map[r][c] == 'O')
            return true;
        int d = "URDL".indexOf(map[r][c]);
        map[r][c] = 'V';
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(!isIn(nr, nc) || recur(nr,nc)) {
            map[r][c] = 'O';
            A++;
            return true;
        } else {
            map[r][c] = 'X';
            return false;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}