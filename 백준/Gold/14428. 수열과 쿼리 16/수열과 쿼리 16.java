import java.io.*;
import java.util.*;

public class Main {
    static int N, startIdx;
    static int[] A;
    static int treeSize;
    static int[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        treeSize = 1;
        while (treeSize <= N * 2)
            treeSize <<= 1;
        tree = new int[treeSize];
        startIdx = treeSize / 2;
        A = new int[N + 1];

        Arrays.fill(A, 100_000_000);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++)
            update(n, Integer.parseInt(st.nextToken()));


        int M = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1)
                update(b, c);
            else
                select(startIdx + b - 1, startIdx + c - 1);
        }
        System.out.println(sb);
    }

    private static void update(int s, int v) {
        A[s] = v;
        int i = startIdx + s - 1;
        tree[i] = s;
        while (i > 1) {
            i >>= 1;
            tree[i] = A[tree[i * 2]] <= A[tree[i * 2 + 1]] ? tree[i * 2] : tree[i * 2 + 1];
        }
    }

    private static void select(int i, int j) {
        int res = 0;
        int max = Integer.MAX_VALUE;
        while (i <= j) {
            if (i % 2 == 1) {
                if (max >= A[tree[i]]) {
                    res = max == A[tree[i]] ? Math.min(res, tree[i]) : tree[i];
                    max = A[tree[i]];
                }
                i++;
            }
            if (j % 2 == 0) {
                if (max >= A[tree[j]]) {
                    res = max == A[tree[j]] ? Math.min(res, tree[j]) : tree[j];
                    max = A[tree[j]];
                }
                j--;
            }
            i /= 2;
            j /= 2;
        }
        sb.append(res).append("\n");
    }
}