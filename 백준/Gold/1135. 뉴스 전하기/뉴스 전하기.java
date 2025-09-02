import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] in = new int[N];
        int[] parent = new int[N];
        List<Integer>[] time = new ArrayList[N];
        Arrays.setAll(time, v -> new ArrayList<>());
        parent[0] = Integer.parseInt(st.nextToken());
        for (int n = 1; n < N; n++) {
            parent[n] = Integer.parseInt(st.nextToken());
            in[parent[n]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 1; n < N; n++) {
            if (in[n] == 0) {
                q.offer(n);
            }
        }
        int answer = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            Collections.sort(time[u]);
            int t = 0;
            for (int v : time[u]) {
                t = Math.max(v, t) + 1;
            }
            if (u == 0) {
                answer = t;
                break;
            }
            time[parent[u]].add(t);
            if (--in[parent[u]] == 0) {
                q.offer(parent[u]);
            }
        }
        System.out.println(answer);
    }
}