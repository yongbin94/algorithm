import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++)
            parent[i] = i;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().charAt(0) == '0')
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            else
                sb.append(find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))
                        ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return true;
        parent[rootA] = rootB;
        return false;
    }
}