import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, R, C, D;
    static int[][] A, B;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = N * M;
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        B = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++)
                A[i][j] = input.charAt(j) - '0';
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++)
                B[i][j] = input.charAt(j) - '0';
        }

        solution();
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void solution() {
        int cnt = 0, nr = -1, nc = -1, nd = -1, count = -1;
        while (isIn(R, C) && K > 0) {
            if (visited[R][C]) {
                D = (D + B[R][C]) % 4;
                if (nr == R + dr[D] && nc == C + dc[D] && nd == D)
                    break;
            } else {
                visited[R][C] = true;
                D = (D + A[R][C]) % 4;
                K--;
                nr = R + dr[D];
                nc = C + dc[D];
                 nd = D;
                count = cnt + 1;
            }
            R += dr[D];
            C += dc[D];
            cnt++;
        }
        System.out.println(count);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
