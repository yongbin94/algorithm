import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[] A;
    static StringBuilder sb = new StringBuilder(), tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            A = new int[N];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    A[n] |= 1 << (Integer.parseInt(st.nextToken()) - 1);
                }
            }
            for (int m = 1; m <= M; m++) {
                if (recur(m, 0, 0, 0)) {
                    sb.append("Data Set ").append(tc).append(":").append(tmp.reverse()).append("\n\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    private static boolean recur(int m, int depth, int start, int value) {
        if (value == (1 << M) - 1) {
            tmp = new StringBuilder();
            return true;
        }
        if (depth == m)
            return false;
        for (int i = start; i < N; i++)
            if (recur(m, depth + 1, i + 1, value | A[i])) {
                tmp.append((char) (i + 'A')).append(" ");
                return true;
            }
        return false;
    }
}