import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int treeSize = 1;
        while (treeSize < N * 2)
            treeSize <<= 1;
        tree = new int[treeSize];
        Arrays.fill(tree, Integer.MAX_VALUE);
        int startIndex = treeSize / 2;
        for (int n = 0; n < N; n++)
            tree[startIndex + n] = Integer.parseInt(br.readLine());
        setTree();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            select(startIndex + Integer.parseInt(st.nextToken()) - 1, startIndex + Integer.parseInt(st.nextToken()) - 1);
        }
        System.out.println(sb);
    }

    private static void setTree() {
        int idx = tree.length / 2;
        while (idx > 0) {
            for (int i = idx / 2; i < idx; i++)
                tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
            idx /= 2;
        }
    }

    private static void select(int i, int j) {
        int res = Integer.MAX_VALUE;
        while (i <= j) {
            if (i % 2 == 1)
                res = Math.min(res, tree[i++]);
            if (j % 2 == 0)
                res = Math.min(res, tree[j--]);
            i /= 2;
            j /= 2;
        }
        sb.append(res).append("\n");
    }
}
