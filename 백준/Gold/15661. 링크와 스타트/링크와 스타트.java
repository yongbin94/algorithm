import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = Integer.MAX_VALUE;
        for (int bit = 1; bit < (1 << N) - 1; bit++) {
            int a = 0, b = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (((bit >> i) & 1) != ((bit >> j) & 1)) continue;
                    if (((bit >> i) & 1) == 0) a += A[i][j] + A[j][i];
                    else b += A[i][j] + A[j][i];
                }
            }
            res = Math.min(res, Math.abs(a - b));
        }
        System.out.println(res);
    }
}