import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        SegTree seg = new SegTree(N, new StringTokenizer(br.readLine()));
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1) seg.change(Integer.parseInt(st.nextToken()) - 1);
            else if (t == 2) i = (i + Integer.parseInt(st.nextToken())) % N;
            else sb.append(seg.find(i)).append("\n");
        }
        System.out.println(sb);

    }

    private static class SegTree {
        final int INF = 100_000_000;
        int n, N;
        int[] tree;

        public SegTree(int N, StringTokenizer st) {
            this.N = N;
            n = 1;
            while (n < N) n <<= 1;
            tree = new int[n * 2];
            Arrays.fill(tree, INF);
            for (int i = 0; i < N; i++) {
                if (st.nextToken().charAt(0) == '1') tree[n + i] = i;
            }
            for (int i = n - 1; i > 0; i--) {
                tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
            }
        }

        public void change(int i) {
            tree[n + i] = tree[n + i] == INF ? i : INF;
            update(n + i);
        }

        private void update(int i) {
            while (i > 1) {
                i >>= 1;
                tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
            }
        }


        public int find(int i) {
            int res = query(n + i, n + N);
            if (res == INF) res = query(n, n + i) + N;
            return res > INF ? -1 : res - i;
        }

        private int query(int l, int r) {
            int res = INF;
            while (l <= r) {
                if ((l & 1) == 1) res = Math.min(res, tree[l++]);
                if ((r & 1) == 0) res = Math.min(res, tree[r--]);
                l >>= 1;
                r >>= 1;
            }
            return res;
        }
    }
}