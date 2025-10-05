import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[M];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            while (i-- > 0) {
                A[m] |= 1 << Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        recur(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void recur(int i, int cnt, int v) {
        if (i == M) {
            if (Integer.bitCount(v) == N) {
                answer = Math.min(answer, cnt);
            }
            return;
        }
        recur(i + 1, cnt, v);
        recur(i + 1, cnt + 1, v | A[i]);
    }
}
