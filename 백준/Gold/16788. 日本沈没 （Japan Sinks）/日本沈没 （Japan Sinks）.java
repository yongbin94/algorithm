import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 1; i <= N; i++) {
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), i});
        }
        if (pq.peek()[0] == 0) {
            System.out.println(0);
            return;
        }
        int cnt = 0, max = 0;
        boolean[] visited = new boolean[N + 2];
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            cnt += getCnt(a[1], visited);
            while(!pq.isEmpty() && a[0] == pq.peek()[0]) {
                cnt += getCnt(pq.poll()[1], visited);
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }

    private static int getCnt(int i, boolean[] visited) {
        visited[i] = true;
        if (visited[i + 1] && visited[i - 1]) return -1;
        else if (visited[i + 1] || visited[i - 1]) return 0;
        else return 1;
    }
}