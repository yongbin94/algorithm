import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static int treeSize;
    static long[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        treeSize = 1;
        while (treeSize <= N * 2)
            treeSize <<= 1;
        tree = new long[treeSize];
        int startIdx = treeSize / 2;

        sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1)
                update(startIdx + b - 1, c);
            else
                select(startIdx + b - 1, startIdx + c - 1);
        }
        System.out.println(sb);
    }

    private static void update(int i, int v) {
        tree[i] += v;
        while (i > 1) {
            i >>= 1;
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private static void select(int i, int j) {
        long res = 0;
        while(i <= j) {
            if(i % 2 == 1)
                res += tree[i++];
            if(j % 2 == 0)
                res += tree[j--];
            i /=2;
            j /=2;
        }
        sb.append(res).append("\n");
    }
}