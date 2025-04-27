import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edges;
    static int[] visited, out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        visited = new int[N + 1];
        out = new int[N + 2];

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        
        int curr = 1, next = 2;
        visited[1] = 1;
        out[1] = 1;
        
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int s = Integer.parseInt(st.nextToken());
            if (visited[s] != curr) {
                System.out.println(0);
                return;
            }
            for (int e : edges[s]) {
                if (visited[e] != 0) continue;
                visited[e] = next;
                out[next]++;
            }
            if (--out[curr] == 0) curr++;
            if (out[next] > 0) next++;
        }
        System.out.println(1);
    }
}