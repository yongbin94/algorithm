import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int treeSize = 1;
        while (treeSize < N * 2)
            treeSize <<= 1;
        int startIndex = treeSize / 2;
        tree = new long[treeSize];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            tree[startIndex + n] = Integer.parseInt(st.nextToken());
        setTree();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            select(startIndex + Integer.parseInt(st.nextToken()) - 1, startIndex + Integer.parseInt(st.nextToken()) - 1);
            update(startIndex + Integer.parseInt(st.nextToken()) - 1, Long.parseLong(st.nextToken()));
        }
        System.out.println(sb);
    }


    private static void setTree() {
        int idx = tree.length / 2;
        while (idx > 1) {
            for (int i = idx / 2; i < idx; i++)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            idx /= 2;
        }
    }

    private static void select(int i, int j) {
        if(i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
        long res = 0;
        while (i <= j) {
            if (i % 2 == 1)
                res += tree[i++];
            if (j % 2 == 0)
                res += tree[j--];
            i /= 2;
            j /= 2;
        }
        sb.append(res).append("\n");
    }

    private static void update(int i, long v) {
        tree[i] = v;
        i /= 2;
        while (i > 0) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
            i /= 2;
        }
    }
}