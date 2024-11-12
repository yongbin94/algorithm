import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            long answer = 0;
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            while (n-- > 0)
                pq.offer(Long.parseLong(st.nextToken()));
            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                answer += a + b;
                pq.offer(a + b);
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}