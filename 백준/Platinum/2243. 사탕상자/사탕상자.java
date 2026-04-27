import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 1_000_000;
    static int[] tree = new int[4_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());

            if (A == 1) {
                int B = Integer.parseInt(st.nextToken());
                int v = query(1, 1, MAX, B);
                sb.append(v).append("\n");
                update(1, 1, MAX, v, -1);
            } else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                update(1, 1, MAX, B, C);
            }
        }
        System.out.print(sb);
    }

    static int query(int node, int start, int end, int k) {
        if (start == end) return start;

        int mid = (start + end) / 2;
        if (tree[node * 2] >= k) {
            return query(node * 2, start, mid, k);
        } else {
            return query(node * 2 + 1, mid + 1, end, k - tree[node * 2]);
        }
    }

    static void update(int node, int start, int end, int target, int diff) {
        if (target < start || target > end) return;

        tree[node] += diff;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(node * 2, start, mid, target, diff);
        update(node * 2 + 1, mid + 1, end, target, diff);
    }
}