import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] jewelNum;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewelNum = new int[N];
        Arrays.fill(jewelNum, -1);
        for (int k = 0; k < K; k++)
            jewelNum[Integer.parseInt(br.readLine()) - 1] = k;

        edges = new ArrayList[N];
        for (int n = 0; n < N; n++)
            edges[n] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        solution();
    }


    private static void solution() {
        Queue<Edge> pq = new ArrayDeque<>();
        pq.offer(new Edge(0, 0));

        boolean[][] visited = new boolean[N][1 << K];
        visited[0][0] = true;

        int answer = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            
            int jewel = jewelNum[s.e];
            if ((s.w & (1 << jewel)) != 0)
                jewel = -1;
            int nbit = s.w | (1 << jewel);
            if(jewel > 0)
                visited[s.e][nbit] = true;
            
            if (s.e == 0)
                answer = Integer.max(answer, Integer.bitCount(jewel >= 0 ? s.w | (1 << jewel) : s.w));

            for (Edge e : edges[s.e]) {
                if (jewel >= 0) {
                    if (e.w >= Integer.bitCount(nbit) && !visited[e.e][nbit]) {
                        visited[e.e][nbit] = true;
                        pq.offer(new Edge(e.e, nbit));
                    }
                }
                if (e.w >= Integer.bitCount(s.w) && !visited[e.e][s.w]) {
                    visited[e.e][s.w] = true;
                    pq.offer(new Edge(e.e, s.w));
                }
            }
        }
        System.out.println(answer);
    }

    private static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}