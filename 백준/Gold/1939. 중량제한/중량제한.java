import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Bridge>[] list = new ArrayList[N + 1];
        for (int n = 1; n <= N; n++)
            list[n] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Bridge(e, w));
            list[e].add(new Bridge(s, w));
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N + 1];
        int answer = Integer.MAX_VALUE;
        int[] max = new int[N + 1];
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        visited[S] = true;
        for (Bridge bridge : list[S])
            pq.offer(bridge);
        while (!pq.isEmpty()) {
            Bridge b = pq.poll();
            if (visited[b.e])
                continue;
            visited[b.e] = true;
            answer = Math.min(answer, b.w);
            if(b.e == E) {
                System.out.println(answer);
                return;
            }
            for (Bridge bridge : list[b.e]) {
                if (visited[bridge.e] || max[bridge.e] >= bridge.w)
                    continue;
                max[bridge.e] = bridge.w;
                pq.offer(bridge);
            }
        }
    }

    private static class Bridge implements Comparable<Bridge> {
        int e, w;

        public Bridge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Bridge o) {
            return o.w - this.w;
        }
    }
}
