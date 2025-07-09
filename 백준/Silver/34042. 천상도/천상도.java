import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            if (N == 1) {
                sb.append(br.readLine()).append("\n");
                continue;
            }
            st = new StringTokenizer(br.readLine());
            int[] A = new int[5];
            for (int n = 0; n < N; n++) {
                A[Integer.parseInt(st.nextToken()) + 2]++;
            }
            long res = 1;
            res <<= A[4];
            res <<= A[0] - (A[0] % 2 == 0 || A[1] > 0 ? 0 : 1);
            if (res == 1 && A[3] == 0) res = A[1] > 1 ? 1 : 0;
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}