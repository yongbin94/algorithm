import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        t:
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            boolean isTree = true;
            parent = new int[N];
            for (int i = 0; i < N; i++)
                parent[i] = i;
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                if (!isTree)
                    continue;
                if(!union(a, b)) {
                    isTree = false;
                }
            }
            int n = find(0);
            if(isTree) {
                for (int i = 1; i < N; i++)
                    if (find(i) != n) {
                        isTree = false;
                        break;
                    }
            }
            sb.append(isTree ? "tree" : "graph").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return false;
        parent[rootB] = rootA;
        return true;
    }

    private static int find(int a) {
        if (a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }
}