import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            A = new int[N][M];
            B = new int[N][M];
            C = new int[N][M];
            int answer = 0;
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v == 0) continue;
                    A[n][m] = m > 0 ? A[n][m - 1] + 1 : 1;
                    B[n][m] = n > 0 ? B[n - 1][m] + 1 : 1;
                    C[n][m] = Math.min(n > 0 && m > 0 ? C[n - 1][m - 1] + 1 : 1, Math.min(A[n][m], B[n][m]));
                    answer = Math.max(answer, C[n][m]);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
