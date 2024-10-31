import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] R, C, map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = new int[N][M];
        C = new int[N][M];
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                if (st.nextToken().charAt(0) == '0') {
                    R[r][c] = (r > 0 ? R[r - 1][c] : 0) + 1;
                    C[r][c] = (c > 0 ? C[r][c - 1] : 0) + 1;
                    map[r][c] = Math.min(R[r][c], C[r][c]);
                if (r > 0 && c > 0)
                        map[r][c] = Math.min(map[r][c], map[r - 1][c - 1] + 1);
                }
                answer = Math.max(answer, map[r][c]);
            }
        }
        System.out.println(answer);
    }
}