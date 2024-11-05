import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        outer1 : while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<String> pq = new PriorityQueue<>();
            for (int n = 0; n < N; n++) {
                pq.offer(br.readLine());
            }
            String prev = pq.poll();
            outer2: while (!pq.isEmpty()) {
                String curr = pq.poll();
                if (prev.length() > curr.length()) {
                    prev = curr;
                    continue;
                }
                for (int i = 0, size = prev.length(); i < size; i++) {
                    if (prev.charAt(i) != curr.charAt(i)) {
                        prev = curr;
                        continue outer2;
                    }
                }
                sb.append("NO").append("\n");
                continue outer1;
            }
            sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }
}