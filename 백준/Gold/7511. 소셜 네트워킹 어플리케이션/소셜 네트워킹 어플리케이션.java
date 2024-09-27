import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());
            parent = new int[N];
            for (int n = 0; n < N; n++)
                parent[n] = n;
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }
            sb.append("Scenario ").append(tc).append(":").append("\n");
            int M = Integer.parseInt(br.readLine());
            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = find(Integer.parseInt(st.nextToken()));
                int b = find(Integer.parseInt(st.nextToken()));
                sb.append(a == b ? 1 : 0).append("\n");;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB)
            return;
        parent[rootB] = rootA;
    }

    private static int find(int a) {
        if(parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
}