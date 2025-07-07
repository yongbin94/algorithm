import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[][] A = new boolean[N][N];
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    A[i][j] |= A[i][k] && A[k][j];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int cnt = 1;
            for (int m = 0; m < N; m++) {
                if(A[n][m] || A[m][n]) cnt++;
            }
            sb.append(N - cnt).append("\n");
        }
        System.out.println(sb);
    }
}