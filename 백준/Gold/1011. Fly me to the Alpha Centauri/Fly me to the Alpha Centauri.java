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
            int v = (int) Math.sqrt(b - a);
            sb.append(v * 2 + (v == Math.sqrt(b - a) ? -1 : (b - a <= v * (v + 1)) ? 0 : 1)).append("\n");
        }
        System.out.println(sb);
    }
}