import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine()), p = 1, res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (N-- > 0) pq.offer(Integer.parseInt(br.readLine()));
        while (!pq.isEmpty()) {
            int v = pq.poll();
            if (p <= v) res += v - p++;
        }
        System.out.println(res);
    }
}