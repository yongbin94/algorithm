import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Route> pq = new PriorityQueue<>();
        for (int[] route : routes) {
            pq.offer(new Route(route[0], route[1]));
        }
        int i = -30001;
        while (!pq.isEmpty()) {
            Route route = pq.poll();
            if (route.s <= i) continue;
            i = route.e;
            answer++;
        }
        return answer;
    }
    
    private static class Route implements Comparable<Route> {
        int s, e;
        public Route (int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Route o) {
            return this.e - o.e;
        }
    }
}