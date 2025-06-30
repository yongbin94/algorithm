import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new long[100_001];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            A[Integer.parseInt(st.nextToken())]++;
        }
        for (int n = 1; n <= 100_000; n++) {
            A[n] = A[n] + A[n - 1];
        }
        st = new StringTokenizer(br.readLine());
        long win = 0, draw = 0, lose = 0;
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            win += N - A[v];
            draw += A[v] - A[v - 1];
            lose += A[v - 1];
        }
        System.out.printf("%d %d %d", win, lose, draw);
    }
}