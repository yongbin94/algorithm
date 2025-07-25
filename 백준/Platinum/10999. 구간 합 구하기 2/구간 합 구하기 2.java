import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        LazySegTree seg = new LazySegTree(N);
        for (int n = 0; n < N; n++) {
            seg.tree[seg.N + n] = Long.parseLong(br.readLine());
        }
        seg.init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            if (type == 1) seg.updateRange(s, e, Long.parseLong(st.nextToken()));
            else sb.append(seg.query(s, e)).append("\n");
        }
        System.out.println(sb);
    }

    private static class LazySegTree {
        int N;
        long[] tree, lazy;

        public LazySegTree(int n) {
            N = 1;
            while (N < n) N <<= 1;
            tree = new long[N * 2];
            lazy = new long[N * 2];
        }

        public void init() {
            for (int i = N - 1; i > 0; i--) {
                tree[i] = tree[i << 1] + tree[i << 1 | 1];
            }
        }

        public void updateRange(int s, int e, long v) {
            updateRange(1, 0, N - 1, s, e, v);
        }

        private void updateRange(int n, int ns, int ne, int s, int e, long v) {
            push(n, ns, ne);
            if (ns > e || ne < s) return;
            if (ns >= s && ne <= e) {
                lazy[n] += v;
                push(n, ns, ne);
                return;
            }
            int mid = (ns + ne) >> 1;
            updateRange(n * 2, ns, mid, s, e, v);
            updateRange(n * 2 + 1, mid + 1, ne, s, e, v);
            tree[n] = tree[n * 2] + tree[n * 2 + 1];
        }

        private void push(int n, int s, int e) {
            tree[n] += lazy[n] * (e - s + 1);
            if (s != e) {
                lazy[n * 2] += lazy[n];
                lazy[n * 2 + 1] += lazy[n];
            }
            lazy[n] = 0;
        }

        public long query(int s, int e) {
            return query(1, 0, N - 1, s, e);
        }

        private long query(int n, int ns, int ne, int s, int e) {
            push(n, ns, ne);
            if (ns > e || ne < s) return 0;
            if (ns >= s && ne <= e) return tree[n];
            int mid = (ns + ne) >> 1;
            return query(n * 2, ns, mid, s, e) + query(n * 2 + 1, mid + 1, ne, s, e);
        }
    }
}