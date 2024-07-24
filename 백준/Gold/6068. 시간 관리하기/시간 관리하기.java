import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int time = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Info v = pq.poll();
            time = Math.min(time, v.s) - v.t;
            if(time < 0) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(time);
    }

    private static class Info implements Comparable<Info> {
        int t, s;

        public Info(int t, int s) {
            this.t = t;
            this.s = s;
        }

        @Override
        public int compareTo(Info o) {
            return o.s - this.s;
        }
    }
}
