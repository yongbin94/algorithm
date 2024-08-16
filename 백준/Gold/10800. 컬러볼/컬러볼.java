import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Ball> pq = new PriorityQueue();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int[] A = new int[N + 1];
        int[] B = new int[N];
        for (int i = 0; i < N + 1; i++)
            A[i] = 0;

        PriorityQueue<Ball> temp = new PriorityQueue<>();
        int totalSum = 0;
        while (!pq.isEmpty()) {
            Ball current = pq.poll();

            while (!temp.isEmpty() && temp.peek().w < current.w) {
                Ball b = temp.poll();
                totalSum += b.w;
                A[b.n] += b.w;
            }

            B[current.i] = totalSum - A[current.n];
            temp.offer(current);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(B[i]).append("\n");
        System.out.println(sb);
    }

    private static class Ball implements Comparable<Ball> {
        int i, n, w, sum;

        public Ball(int i, int n, int w) {
            this.i = i;
            this.n = n;
            this.w = w;
            this.sum = w;
        }

        @Override
        public int compareTo(Ball o) {
            return this.w - o.w;
        }
    }
}