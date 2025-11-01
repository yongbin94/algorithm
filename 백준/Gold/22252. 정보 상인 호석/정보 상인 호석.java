import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        long answer = 0;
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            if (!map.containsKey(name)) map.put(name, new PriorityQueue<>((o1, o2) -> o2 - o1));
            PriorityQueue<Integer> pq = map.get(name);
            if (q == 1) {
                int k = Integer.parseInt(st.nextToken());
                while (k-- > 0) pq.offer(Integer.parseInt(st.nextToken()));
            } else {
                int b = Math.min(Integer.parseInt(st.nextToken()), pq.size());
                while (b-- > 0) answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}