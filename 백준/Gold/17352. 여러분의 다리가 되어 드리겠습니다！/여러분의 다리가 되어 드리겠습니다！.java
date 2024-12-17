import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int n = 0; n <= N; n++)
            parent[n] = n;
        for (int n = 2; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        for (int n = 2; n <= N; n++) {
            if (find(1) != find(n)) {
                System.out.println(1 + " " + n);
                return;
            }
        }
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return;
        parent[rootB] = rootA;
    }

    private static int find(int a) {
        if (a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }
}