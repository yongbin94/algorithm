import java.io.*;
import java.util.*;

public class Main {
    static int N, M, num, answer;
    static boolean[] visited;
    static List<Integer>[] edges;
    static Queue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N];
            answer = 0;
            num = 0;
            pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
            for (int n = 0; n < N; n++)
                edges[n] = new ArrayList<>();

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                edges[s].add(e);
            }
            
            for (int n = 0; n < N; n++)
                edges[n].sort((o1, o2) -> o2 - o1);

            visited = new boolean[N];
            for (int i = 0; i < N; i++)
                if (!visited[i])
                    dfs(i);

            visited = new boolean[N];
            while (!pq.isEmpty()) {
                int[] arr = pq.poll();
                if (visited[arr[1]])
                    continue;
                dfs2(arr[1]);
                answer++;
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int n) {
        visited[n] = true;
        for (int edge : edges[n]) {
            if (visited[edge])
                continue;
            dfs(edge);
        }
        pq.offer(new int[]{num++, n});
    }

    private static void dfs2(int n) {
        visited[n] = true;
        for (int edge : edges[n]) {
            if (visited[edge])
                continue;
            dfs2(edge);
        }
    }
}