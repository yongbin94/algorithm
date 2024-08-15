import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] A;
    static PriorityQueue<Info> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;
        pq = new PriorityQueue<>();
        A = new int[N];
        Arrays.fill(A, Integer.MIN_VALUE);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens())
                pq.offer(new Info(i, Integer.parseInt(st.nextToken())));
        }
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            A[info.i] = info.w;
            int max = Arrays.stream(A).max().getAsInt();
            int min = Arrays.stream(A).min().getAsInt();
            if (min == Integer.MIN_VALUE)
                continue;
            answer = Math.min(answer, max - min);
        }
        System.out.println(answer);
    }

    private static class Info implements Comparable<Info> {
        int i, w;

        public Info(int i, int w) {
            this.i = i;
            this.w = w;
        }

        @Override
        public int compareTo(Info o) {
            return this.w - o.w;
        }
    }
}