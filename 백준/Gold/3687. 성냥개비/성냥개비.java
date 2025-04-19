import java.io.*;
import java.util.*;

public class Main {
    static long[] min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        init();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int v = Integer.parseInt(br.readLine());
            sb.append(min[v]).append(v % 2 == 0 ? " 1" : " 7");
            for (int i = 3; i < v; i += 2) {
                sb.append("1");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        min = new long[101];
        Arrays.fill(min, Long.MAX_VALUE);
        int[] tmp = {1, 7, 4, 2, 6, 8, 10};
        int[] A = {1, 7, 4, 2, 0, 8};
        for (int i = 2; i <= 100; i++) {
            if (i < 9) {
                min[i] = tmp[i - 2];
                continue;
            }
            for (int j = 2; j <= 7; j++) {
                min[i] = Math.min(min[i], min[i - j] * 10 + A[j - 2]);
            }
        }
    }
}