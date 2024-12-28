import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 1_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long[][] A = new long[8][2];
        long[][] B = new long[8][8];
        for (int n = 0; n < 8; n++)
            Arrays.fill(B[n], INF);
        for (int n = 0; n < 8; n++) {
            st = new StringTokenizer(br.readLine());
            A[n][0] = Long.parseLong(st.nextToken());
            A[n][1] = Long.parseLong(st.nextToken());
            if (n > 1) {
                n++;
                A[n][0] = Long.parseLong(st.nextToken());
                A[n][1] = Long.parseLong(st.nextToken());
                B[n - 1][n] = 10;
                B[n][n - 1] = 10;
            }
        }
        for (int n = 0; n < 8; n++)
            for (int m = 0; m < 8; m++)
                B[n][m] = Math.min(B[n][m], Math.abs(A[n][0] - A[m][0]) + Math.abs(A[n][1] - A[m][1]));

        for (int k = 0; k < 8; k++)
            for (int n = 0; n < 8; n++)
                for (int m = 0; m < 8; m++)
                    B[n][m] = Math.min(B[n][m], B[n][k] + B[k][m]);

        System.out.println(B[0][1]);
    }
}