import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Homework> pq = new PriorityQueue<>();
        while (N-- > 0) {
            pq.add(new Homework(new StringTokenizer(br.readLine())));
        }
        int res = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Homework h = pq.poll();
            res = Math.min(res - h.d, h.t - h.d);
        }
        System.out.println(res);
    }

    private static class Homework implements Comparable<Homework> {
        int d, t;

        public Homework(StringTokenizer st) {
            this.d = Integer.parseInt(st.nextToken());
            this.t = Integer.parseInt(st.nextToken());
        }

        @Override
        public int compareTo(Homework o) {
            return o.t - this.t;
        }
    }
}