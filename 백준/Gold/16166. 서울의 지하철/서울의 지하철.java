import java.io.*;
import java.util.*;

public class Main {
    static int N, G;
    static Set<Integer>[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new HashSet[N];
        Arrays.setAll(line, v -> new HashSet<>());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int m = 0; m < M; m++) {
                line[n].add(Integer.parseInt(st.nextToken()));
            }
        }
        G = Integer.parseInt(br.readLine());
        System.out.println(solution());
    }

    private static int solution() {
        int res = 0;
        boolean[] visited = new boolean[N];
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 0; n < N; n++) {
            if (line[n].contains(0)) {
                q.offer(n);
                visited[n] = true;
            }
        }
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int u = q.poll();
                for (int v : line[u]) {
                    if (v == G) return res;
                    for (int n = 0; n < N; n++) {
                        if(visited[n] || !line[n].contains(v)) continue;
                        q.offer(n);
                        visited[n] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
}