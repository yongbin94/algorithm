import java.io.*;
import java.util.*;

public class Main {
    static int N, R, C, W;
    static int[][] A;
    static boolean[][] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken()) / 2;

        A = new int[R + 1][C + 1];
        B = new boolean[R + 1][C + 1];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[r][c] = 1;
            B[r][c] = true;
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                A[r][c] += A[r - 1][c] + A[r][c - 1] - A[r - 1][c - 1];
            }
        }

        int max = 0, resR = 0, resC = 0;
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (B[r][c]) continue;
                int maxR = Math.min(R, r + W);
                int maxC = Math.min(C, c + W);
                int minR = Math.max(0, r - W - 1);
                int minC = Math.max(0, c - W - 1);
                int v = A[maxR][maxC] - A[minR][maxC] - A[maxR][minC] + A[minR][minC];
                if (max >= v) continue;
                max = v;
                resR = r;
                resC = c;
            }
        }
        System.out.printf("%d\n%d %d", max, resR, resC);
    }
}