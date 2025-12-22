import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                A[r][c] = Integer.parseInt(st.nextToken()) + A[r - 1][c] + A[r][c - 1] - A[r - 1][c - 1];
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int er = 1; er <= N; er++) {
            for (int ec = 1; ec <= M; ec++) {
                for (int sr = 0; sr < er; sr++) {
                    for (int sc = 0; sc < ec; sc++) {
                        answer = Math.max(answer, A[er][ec] - A[er][sc] - A[sr][ec] + A[sr][sc]);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}