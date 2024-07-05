import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
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
        PriorityQueue<Integer> a = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> b = new PriorityQueue<>((o1, o2) -> o2 - o1);
        while(!input.isEmpty()) {
            Pacakge p = input.poll();
            while(!a.isEmpty() && a.peek() <= p.s) {
                int i = a.poll();
                answer += A[i];
                boxCnt -= A[i];
                A[i] = 0;
            }
            int cnt = Math.min(p.w, C - boxCnt);
            while(cnt != p.w && !b.isEmpty() && b.peek() > p.e) {
                int i = b.poll();
                int tmp = Math.min(C, Math.min(p.w, cnt + A[i])) - cnt;
                if(A[i] - tmp != 0)
                    b.offer(i);
                A[i] -= tmp;
                boxCnt -= tmp;
                cnt += tmp;
            }
            boxCnt += cnt;
            A[p.e] += cnt;
            a.offer(p.e);
            b.offer(p.e);
        }
        while(!a.isEmpty()) {
            int i = a.poll();
            answer += A[i];
            A[i] = 0;
        }
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
            return this.s == o.s ? this.e - o.e : this.s - o.s;
        }
    }
}