import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        long total = 0, sum = 0;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            pq.offer(new long[]{x, a});
            total += a;
        }
        while (!pq.isEmpty()) {
            long[] a = pq.poll();
            sum += a[1];
            if (sum >= (total + 1) / 2) {
                System.out.println(a[0]);
                return;
            }
        }
    }
}