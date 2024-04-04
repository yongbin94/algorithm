import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        boolean[] isSummit = new boolean[n + 1];
        for(int summit : summits)
            isSummit[summit] = true;
        
        List<Edge>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();
        for(int i = 0; i < paths.length; i++) {
            list[paths[i][0]].add(new Edge(paths[i][1], paths[i][2]));
            list[paths[i][1]].add(new Edge(paths[i][0], paths[i][2]));
        }
        
        int[] answer = { 0, Integer.MAX_VALUE };
        for(int gate : gates) {        
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(gate, 0));
            int[] arr = new int[n + 1];
            Arrays.fill(arr, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n + 1];
            int cnt = 0;
            while(!pq.isEmpty()) {
                Edge s = pq.poll();
                if(answer[1] < s.w)
                    break;
                visited[s.e] = true;
                if(isSummit[s.e] && answer[1] >= s.w) {
                    if(answer[1] != s.w || answer[0] > s.e)
                        answer =  new int[]{ s.e, s.w };
                    continue;
                }
                for(Edge edge : list[s.e]) {
                    if(visited[edge.e] || arr[edge.e] <= edge.w)
                        continue;
                    arr[edge.e] = edge.w;
                    pq.offer(new Edge(edge.e, Math.max(s.w ,edge.w)));
                }
            }
        }
        return answer;
    }
    static class Edge implements Comparable<Edge> {
        int e, w;
        Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
        @Override
        public String toString() {
            return "e: " + this.e + ", w: " + this.w;
        }

        public int compareTo(Edge o) {
            return this.w != o.w ? this.w - o.w : this.e - o.e;
        }
    }
}
