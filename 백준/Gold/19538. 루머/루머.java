import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] in = new int[N + 1];
        int[] A = new int[N + 1];
        Arrays.fill(A, -1);
        List<Integer>[] edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 0) break;
                edges[n].add(v);
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            A[v] = 0;
            q.offer(v);
        }
        int time = 1;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int u = q.poll();
                for (int v : edges[u]) {
                    if (A[v] != -1 || ++in[v] < (edges[v].size() + 1) / 2) continue;
                    A[v] = time;
                    q.offer(v);
                }
            }
            time++;
        }
        StringBuilder sb = new StringBuilder();
        for (int n = 1; n <= N; n++) {
            sb.append(A[n]).append(" ");
        }
        System.out.println(sb);
    }
}