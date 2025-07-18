import java.io.*;
import java.util.*;

public class Main {
    static int N, M, tmp, answer;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N * M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                A[(n * M + m)] = input.charAt(m) - '0';
            }
        }
        recur(0, 0, 0);
        System.out.println(answer);
    }

    private static void recur(int cnt, int bit, int sum) {
        if (cnt == N * M) {
            answer = Math.max(answer, sum);
            return;
        }
        for (int i = 0; i < N * M; i++) {
            if (((bit >> i) & 1) == 1) continue;
            recur(cnt + 1, bit | (1 << i), sum + A[i]);
            for (int x = 1; x < 4; x++) {
                int v = getVertical(i, x, bit);
                if (v > 0) recur(cnt + x + 1, bit | v, sum + tmp);
                int h = getHorizontal(i, x, bit);
                if (h > 0) recur(cnt + x + 1, bit | h, sum + tmp);
            }
            return;
        }
    }


    private static int getVertical(int i, int X, int bit) {
        int res = 1 << i;
        tmp = A[i];
        for (int x = 1; x <= X; x++) {
            int a = i + x * M;
            if (a >= N * M) return 0;
            if (((bit >> a) & 1) == 1) return 0;
            tmp *= 10;
            tmp += A[a];
            res |= 1 << a;
        }
        return res;
    }

    private static int getHorizontal(int i, int X, int bit) {
        int res = 1 << i;
        tmp = A[i];
        if (i % M + X >= M) return 0;
        for (int x = 1; x <= X; x++) {
            int a = i + x;
            if (((bit >> a) & 1) == 1) return 0;
            tmp *= 10;
            tmp += A[a];
            res |= 1 << a;
        }
        return res;
    }
}