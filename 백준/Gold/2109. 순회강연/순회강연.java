import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Info> input = new PriorityQueue<>((o1, o2) -> o2.d - o1.d);
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            input.offer(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int day = Integer.MAX_VALUE, cnt = 0, answer = 0;
        while (!input.isEmpty()) {
            while (!input.isEmpty() && input.peek().d == day)
                pq.offer(input.poll().p);
            for (int i = input.isEmpty() ? 0 : input.peek().d; i < day; i++) {
                if (pq.isEmpty())
                    break;
                answer += pq.poll();
            }
            if(input.isEmpty())
                break;
            day = input.peek().d;
        }
        System.out.println(answer);
    }

    private static class Info {
        int p, d;

        public Info(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }
}