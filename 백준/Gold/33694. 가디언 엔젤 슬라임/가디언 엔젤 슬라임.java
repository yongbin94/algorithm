import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static PriorityQueue<Slime> slimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        slimes = new PriorityQueue<>();
        while (N-- > 0) {
            new Slime().set(new StringTokenizer(br.readLine()));
        }
        long prev = 0;
        long res = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while (!slimes.isEmpty() || !pq.isEmpty()) {
            long curr = !slimes.isEmpty() ? slimes.peek().s : Long.MAX_VALUE;
            while (!pq.isEmpty() && pq.peek() < curr) {
                res += pq.size() >= 3 ? pq.peek() - prev : 0;
                prev = pq.poll();
            }
            res += pq.size() >= 3 ? curr - prev : 0;
            prev = curr;
            while (!slimes.isEmpty() && curr == slimes.peek().s) {
                pq.offer(slimes.poll().e);
            }
        }
        System.out.println(res);
    }

    private static class Slime implements Comparable<Slime> {
        long s, e;

        public void set(StringTokenizer st) {
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            this.s = t + (X - 1) / a;
            this.e = t + (c - 1) / a + 1 + (((c - 1) / a + 1) * a - X) / b;
            if (this.s < this.e) slimes.offer(this);
        }

        @Override
        public int compareTo(Slime o) {
            return Long.compare(this.s, o.s);
        }
    }
}