import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        Queue<Integer> tmp = new ArrayDeque<>();
        int maxValue = 0;
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                int v = getValue(r, c);
                maxValue = Math.max(maxValue, v);
                tmp.offer(v);
            }
        }
        int len = ("" + maxValue).length();
        StringBuilder sb = new StringBuilder();
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                sb.append(String.format("%" + len + "d ", tmp.poll()));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int getValue(int r, int c) {
        int max = Math.max(Math.abs(r), Math.abs(c));
        int prev = ((max - 1) * 2 + 1) * ((max - 1) * 2 + 1);
        if (c == max && r != max) {
            return prev + max - r;
        } else if (r == -max) {
            return prev + max * 2 + max - c;
        } else if (c == -max) {
            return prev + max * 4 + max + r;
        } else {
            return prev + max * 6 + max + c;
        }
    }
}