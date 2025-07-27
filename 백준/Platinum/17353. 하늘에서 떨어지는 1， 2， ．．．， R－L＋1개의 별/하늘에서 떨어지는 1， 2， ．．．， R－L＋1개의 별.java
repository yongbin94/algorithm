import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LazySegTree seg = new LazySegTree(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            seg.tree[seg.N + n] = Integer.parseInt(st.nextToken());
        }
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().charAt(0) == '1')
                seg.updateRange(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            else sb.append(seg.query(Integer.parseInt(st.nextToken()) - 1)).append("\n");
        }
        System.out.println(sb);
    }

    private static class LazySegTree {
        int N;
        long[] tree, lazyA, lazyC;

        public LazySegTree(int n) {
            N = 1;
            while (N < n) N <<= 1;
            tree = new long[N * 2];
            lazyA = new long[N * 2];
            lazyC = new long[N * 2];
        }

        public void updateRange(int l, int r) {
            updateRange(1, 0, N - 1, l, r);
        }

        private void updateRange(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return;
            if (s >= l && e <= r) {
                lazyA[n] += s - l;
                lazyC[n] += 1;
                push(n, s, e);
                return;
            }
            int mid = (s + e) >> 1;
            updateRange(n * 2, s, mid, l, r);
            updateRange(n * 2 + 1, mid + 1, e, l, r);
        }

        public long query(int i) {
            return query(1, 0, N - 1, i);
        }

        private long query(int n, int s, int e, int i) {
            push(n, s, e);
            if (s > i || e < i) return 0;
            if (s == e) return tree[n];
            int mid = (s + e) / 2;
            return query(n * 2, s, mid, i) + query(n * 2 + 1, mid + 1, e, i);
        }

        private void push(int n, int s, int e) {
            long d = e - s + 1;
            if (s != e) {
                lazyA[n * 2] += lazyA[n];
                lazyA[n * 2 + 1] += lazyA[n] + d / 2 * lazyC[n];
                lazyC[n * 2] += lazyC[n];
                lazyC[n * 2 + 1] += lazyC[n];
            } else {
                tree[n] += lazyA[n] * d + d * (d + 1) / 2 * lazyC[n];
            }
            lazyA[n] = 0;
            lazyC[n] = 0;
        }
    }
}