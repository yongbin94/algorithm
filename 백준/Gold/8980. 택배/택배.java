import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        PriorityQueue<Pacakge> input = new PriorityQueue<>();
        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            input.offer(new Pacakge(s, e, w));
        }
        int boxCnt = 0, answer = 0;
        PriorityQueue<Delivery> pq = new PriorityQueue<>();
        while(!input.isEmpty()) {
            Pacakge p = input.poll();
            while(!pq.isEmpty() && pq.peek().e <= p.s) {
                Delivery d = pq.poll();
                answer += d.w;
                boxCnt -= d.w;
            }
            int cnt = Math.min(p.w, C - boxCnt);
            boxCnt += cnt;
            pq.offer(new Delivery(p.e, cnt));
        }
        while(!pq.isEmpty())
            answer += pq.poll().w;

        System.out.println(answer);
    }
    private static class Pacakge implements Comparable<Pacakge> {
        int s,e,w;

        public Pacakge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Pacakge o) {
            return this.e == o.e ? this.s - o.s : this.e - o.e;
        }
    }

    private static class Delivery implements Comparable<Delivery> {
        int e, w;

        public Delivery(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Delivery o) {
            return this.e - o.e;
        }
    }
}
