import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        LazySegTree seg = new LazySegTree(N);
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;
            if (o == 0) seg.updateRange(s, t);
            else sb.append(seg.query(s, t)).append("\n");
        }
        System.out.println(sb);
    }

    private static class LazySegTree {
        int N;
        int[] tree;
        boolean[] lazy;

        public LazySegTree(int n) {
            N = 1;
            while (N < n) N <<= 1;
            tree = new int[N * 2];
            lazy = new boolean[N * 2];
        }

        public void updateRange(int l, int r) {
            updateRange(1, 0, N - 1, l, r);
        }

        private void updateRange(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return;
            if (s >= l && e <= r) {
                lazy[n] = true;
                push(n, s, e);
                return;
            }
            int mid = (s + e) >> 1;
            updateRange(n * 2, s, mid, l, r);
            updateRange(n * 2 + 1, mid + 1, e, l, r);
            tree[n] = tree[n * 2] + tree[n * 2 + 1];
        }

        public int query(int l, int r) {
            return query(1, 0, N - 1, l, r);
        }

        private int query(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return 0;
            if (s >= l && e <= r) return tree[n];
            int mid = (s + e) >> 1;
            return query(n * 2, s, mid, l, r) + query(n * 2 + 1, mid + 1, e, l, r);
        }

        private void push(int n, int s, int e) {
            if (!lazy[n]) return;
            tree[n] = (e - s + 1) - tree[n];
            if (s != e) {
                lazy[n * 2] = !lazy[n * 2];
                lazy[n * 2 + 1] = !lazy[n * 2 + 1];
            }
            lazy[n] = false;
        }
    }
}