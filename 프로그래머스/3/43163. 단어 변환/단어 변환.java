import java.util.*;
class Solution {
    static List<Integer>[] edges;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int S = 0, E = -1;
        edges = new ArrayList[words.length + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int i = 0; i <= words.length; i++) {
            String a = (i == 0) ? begin : words[i - 1];
            if (target.equals(a)) E = i;
            for (int j = i + 1; j <= words.length; j++) {
                String b = words[j - 1];
                int diff = 0;
                for (int k = 0; k < a.length(); k++) {
                    if (a.charAt(k) != b.charAt(k)) diff++;
                }
                if (diff > 1) continue;
                edges[i].add(j);
                edges[j].add(i);
            }
        }
        if (E == -1) return 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] visited = new boolean[words.length + 1];
        visited[0] = true;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int u = q.poll();
                if (u == E) return answer;
                for (int v : edges[u]) {
                    if (visited[v]) continue;
                    visited[v] = true;
                    q.offer(v);
                }
            }
            answer++;
        }
        return 0;
    }
}