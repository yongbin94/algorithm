import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = 0, b = 0, c = 0;
        while (N-- > 0) {
            int v = Integer.parseInt(st.nextToken());
            c += (a + v) / 3;
            a = (a + v) % 3;
            b += v / 2;
        }
        System.out.println(a == 0 && b >= c ? "YES" : "NO");
    }
}