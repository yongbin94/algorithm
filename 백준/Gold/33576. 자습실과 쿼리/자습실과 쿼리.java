import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        long[] A = new long[N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            A[w] = d;
        }
        for (int i = 1; i <= N; i++) {
            A[i] += A[i - 1];
        }
        int l = 0, r = N;
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            int i = Integer.parseInt(br.readLine());
            long a = A[i] - A[l];
            long b = A[r] - A[i];
            if (l > i || r < i) {
                sb.append("0\n");
            } else if (a > b) {
                r = i;
                sb.append(b).append("\n");
            } else if (a < b) {
                l = i;
                sb.append(a).append("\n");
            } else if (i - 1 > N - i) {
                r = i;
                sb.append(b).append("\n");
            } else {
                l = i;
                sb.append(a).append("\n");
            }
        }
        System.out.println(sb);
    }
}