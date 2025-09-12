import java.io.*;
import java.util.*;

public class Main {
    static int N, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        PriorityQueue<Menu> pq = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            pq.offer(new Menu(new StringTokenizer(br.readLine())));
        }
        int a = (X - N * 1000) / 4000;
        int answer = 0;
        while (!pq.isEmpty() && a-- > 0 && pq.peek().d > 0) {
            answer += pq.poll().a;
        }
        while (!pq.isEmpty()) {
            answer += pq.poll().b;
        }
        System.out.println(answer);
    }

    private static class Menu implements Comparable<Menu> {
        int a, b, d;

        public Menu(StringTokenizer st) {
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = a - b;
        }

        @Override
        public int compareTo(Menu o) {
            return o.d - this.d;
        }
    }
}