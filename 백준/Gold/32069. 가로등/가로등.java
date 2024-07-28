import java.io.*;
import java.util.*;

public class Main {
    static int L, N, K;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[L + 1];
        Arrays.fill(map, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            map[v] = 0;
            q.offer(v);
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i > 0 && map[i - 1] > map[i] + 1) {
                map[i - 1] = map[i] + 1;
                q.offer(i - 1);
            }
            if (i < L && map[i + 1] > map[i] + 1) {
                map[i + 1] = map[i] + 1;
                q.offer(i + 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int v : map)
            pq.offer(v);
        StringBuilder sb = new StringBuilder();
        while (K-- > 0)
            sb.append(pq.poll()).append("\n");
        System.out.println(sb);
    }
}