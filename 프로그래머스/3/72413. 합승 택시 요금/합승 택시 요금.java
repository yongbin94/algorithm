import java.util.*;
class Solution {
    static int A, B, N;
    static List<Edge>[] list;
    static boolean[] visited;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        A = a;
        B = b;
        N = n;
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();
        for(int[] f : fares) {
            list[f[0]].add(new Edge(f[1], f[2]));
            list[f[1]].add(new Edge(f[0], f[2]));
        }
        int[][] memo = new int[3][n + 1];
        Arrays.stream(memo).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
        dijkstra(memo[0], s);
        dijkstra(memo[1], a);
        dijkstra(memo[2], b);
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++)
            answer = Math.min(answer, memo[0][i] + memo[1][i] + memo[2][i]);
        
        return answer;
    }
    
    private void dijkstra(int[] memo, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        memo[start] = 0;
        visited = new boolean[N + 1];
        boolean a = false, b = false;
        while(!pq.isEmpty()) {
            Edge s = pq.poll();
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            for(Edge e : list[s.e]) {
                if(visited[e.e] || memo[e.e] <= s.w + e.w)
                    continue;
                memo[e.e] = s.w + e.w;
                pq.offer(new Edge(e.e, s.w + e.w));
            }
        }
    }
    private static class Edge implements Comparable<Edge> {
        int e, w;
        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    } 
}