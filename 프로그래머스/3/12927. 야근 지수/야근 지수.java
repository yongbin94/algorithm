import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        int total = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int a : works) {
            pq.offer(a);
            total += a;
        }
        if (total < n) return answer;
        while (n-- > 0) {
            int v = pq.poll() - 1;
            if(v > 0) pq.offer(v);
        }
        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2); 
        }
        return answer;
    }
}