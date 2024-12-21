import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int n = 0; n < N; n++)
            parent[n] = n;
        int answer = 0;
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {
                answer = m;
                break;
            }

        }
        System.out.println(answer);
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
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
}