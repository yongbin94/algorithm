import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int ab = Integer.parseInt(st.nextToken());
            int bc = Integer.parseInt(st.nextToken());
            int ca = Integer.parseInt(st.nextToken());

            int res = 0;
            for (int i = 0; i <= Math.min(a, b); i++) {
                for (int j = 0; j <= Math.min(b - i, c); j++) {
                    int k = Math.max(0, Math.min(a - i, c - j));
                    res = Math.max(res, ab * i + bc * j + ca * k);
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}