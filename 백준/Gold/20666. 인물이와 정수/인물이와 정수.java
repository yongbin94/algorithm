import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        int P = Integer.parseInt(br.readLine());
        List<Info>[] E = new ArrayList[N + 1];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long t = Long.parseLong(st.nextToken());

            if (E[a] == null) E[a] = new ArrayList<>();
            E[a].add(new Info(b, t));
            A[b] += t;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            pq.offer(new Info(i, A[i]));
        }

        boolean[] visited = new boolean[N + 1];
        long answer = 0;
        int count = 0;

        while (!pq.isEmpty() && count < M) {
            Info u = pq.poll();
            if (visited[u.n]) continue;
            visited[u.n] = true;
            count++;
            answer = Math.max(answer, u.t);
            if (E[u.n] == null) continue;
            for (Info v : E[u.n]) {
                if (!visited[v.n]) {
                    A[v.n] -= v.t;
                    pq.offer(new Info(v.n, A[v.n]));
                }
            }
        }
        System.out.println(answer);
    }

    private static class Info implements Comparable<Info> {
        int n;
        long t;

        public Info(int n, long t) {
            this.n = n;
            this.t = t;
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(this.t, o.t);
        }
    }
}