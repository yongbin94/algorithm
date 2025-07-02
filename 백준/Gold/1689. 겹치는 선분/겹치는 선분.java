import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> input = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            input.offer(new int[]{s, e});
        }
        int res = 0;
        while (!input.isEmpty()) {
            int[] a = input.poll();
            while(!pq.isEmpty() && pq.peek() <= a[0]) {
                pq.poll();
            }
            pq.offer(a[1]);
            res = Math.max(res, pq.size());
        }
        System.out.println(res);
    }
}