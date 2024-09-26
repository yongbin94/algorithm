import java.io.*;
import java.util.*;

public class Main {
    static int N, H;
    static int[] top, bottom;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        top = new int[H + 1];
        bottom = new int[H + 1];

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[h]++;
            } else {
                top[h]++;
            }
        }

        for (int i = H - 1; i >= 1; i--) {
            top[i] += top[i + 1];
            bottom[i] += bottom[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= H; i++) {
            int v = top[i] + bottom[H - i + 1];
            if (v < min) {
                min = v;
                count = 1;
            } else if (v == min)
                count++;
        }
        System.out.println(min + " " + count);
    }
}