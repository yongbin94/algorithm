import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int treeSize = 1;
        while (treeSize <= N * 2)
            treeSize <<= 1;
        tree = new long[treeSize];
        int startIndex = treeSize / 2;
        for (int n = 0; n < N; n++)
            update(startIndex + n, Long.parseLong(br.readLine()));
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1)
                update(startIndex + (int) b - 1, c);
            else
                select(startIndex + (int) b - 1, startIndex + (int) c - 1);
        }
        System.out.println(sb);
    }

    private static void update(int index, long value) {
        tree[index] = value;
        while (index > 1) {
            tree[index / 2] = tree[index] + (index % 2 == 0 ? tree[index + 1] : tree[index - 1]);
            index /= 2;
        }
    }

    private static void select(int i, int j) {
        long sum = 0;
        while (i <= j) {
            if (i % 2 == 1) {
                sum += tree[i++];
            }
            if (j % 2 == 0) {
                sum += tree[j--];
            }
            i /= 2;
            j /= 2;
        }
        sb.append(sum).append("\n");
    }
}