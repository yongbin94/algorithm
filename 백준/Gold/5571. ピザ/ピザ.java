import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);
        ts.add(D);
        for (int n = 1; n < N; n++) {
            ts.add(Integer.parseInt(br.readLine()));
        }
        long answer = 0;
        while (M-- > 0) {
            int v = Integer.parseInt(br.readLine());
            answer += Math.min(v - ts.floor(v), ts.ceiling(v) - v);
        }
        System.out.println(answer);
    }
}