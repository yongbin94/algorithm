import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long f = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        long answer = W * H;
        long a = Math.min(f, W - f);
        if (x1 < a) answer -= (Math.min(x2, a) - x1) * (c + 1) * (y2 - y1) * 2;
        if (x2 > a) answer -= (x2 - Math.max(x1, a)) * (c + 1) * (y2 - y1);
        System.out.println(answer);
    }
}