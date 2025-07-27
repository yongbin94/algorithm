import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HeavyLightDecomposition hld = new HeavyLightDecomposition(N);
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            hld.addEdge(a, b);
        }
        hld.build();
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            char w = st.nextToken().charAt(0);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (w == 'P') hld.updatePath(u, v);
            else sb.append(hld.queryPath(u, v)).append("\n");
        }
        System.out.println(sb);
    }

    private static class HeavyLightDecomposition {
        int N, i = 0;
        List<Integer>[] edges;
        int[] parents, depth, size, heavy, head, idx;
        LazySegmentTree seg;

        public HeavyLightDecomposition(int n) {
            N = n;
            edges = new ArrayList[N + 1];
            Arrays.setAll(edges, v -> new ArrayList<>());
            parents = new int[N + 1];
            depth = new int[N + 1];
            size = new int[N + 1];
            heavy = new int[N + 1];
            head = new int[N + 1];
            idx = new int[N + 1];
            seg = new LazySegmentTree(n);
        }

        public void addEdge(int a, int b) {
            edges[a].add(b);
            edges[b].add(a);
        }

        public void build() {
            dfs1(1, 0);
            dfs2(1, 1);
        }

        private void dfs1(int u, int p) {
            parents[u] = p;
            depth[u] = depth[p] + 1;
            size[u] = 1;
            heavy[u] = -1;
            for (int v : edges[u]) {
                if (v == p) continue;
                dfs1(v, u);
                if (heavy[u] == -1 || size[v] > size[heavy[u]]) {
                    heavy[u] = v;
                }
                size[u] += size[v];
            }
        }

        private void dfs2(int u, int h) {
            head[u] = h;
            idx[u] = i++;
            if (heavy[u] != -1) {
                dfs2(heavy[u], h);
            }
            for (int v : edges[u]) {
                if (v == parents[u] || v == heavy[u]) continue;
                dfs2(v, v);
            }
        }

        public void updatePath(int u, int v) {
            while (head[u] != head[v]) {
                if (depth[head[u]] > depth[head[v]]) {
                    seg.updateRange(idx[head[u]], idx[u]);
                    u = parents[head[u]];
                } else {
                    seg.updateRange(idx[head[v]], idx[v]);
                    v = parents[head[v]];
                }
            }
            if (depth[u] < depth[v]) seg.updateRange(idx[heavy[u]], idx[v]);
            else seg.updateRange(idx[heavy[v]], idx[u]);
        }

        public long queryPath(int u, int v) {
            long res = 0;
            while (head[u] != head[v]) {
                if (depth[head[u]] > depth[head[v]]) {
                    res += seg.query(idx[head[u]], idx[u]);
                    u = parents[head[u]];
                } else {
                    res += seg.query(idx[head[v]], idx[v]);
                    v = parents[head[v]];
                }
            }
            if (depth[u] < depth[v]) res += seg.query(idx[heavy[u]], idx[v]);
            else res += seg.query(idx[heavy[v]], idx[u]);
            return res;
        }
    }

    private static class LazySegmentTree {
        int N;
        long[] tree, lazy;

        public LazySegmentTree(int n) {
            N = 1;
            while (N < n) N <<= 1;
            tree = new long[N * 2];
            lazy = new long[N * 2];
        }

        public void updateRange(int l, int r) {
            updateRange(1, 0, N - 1, l, r);
        }

        private void updateRange(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return;
            if (s >= l && e <= r) {
                lazy[n]++;
                push(n, s, e);
                return;
            }
            int mid = (s + e) >> 1;
            updateRange(n * 2, s, mid, l, r);
            updateRange(n * 2 + 1, mid + 1, e, l, r);
            tree[n] = tree[n * 2] + tree[n * 2 + 1];
        }

        public long query(int l, int r) {
            return query(1, 0, N - 1, l, r);
        }

        private long query(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return 0;
            if (s >= l && e <= r) return tree[n];
            int mid = (s + e) >> 1;
            return query(n * 2, s, mid, l, r) + query(n * 2 + 1, mid + 1, e, l, r);
        }

        private void push(int n, int s, int e) {
            tree[n] += (e - s + 1) * lazy[n];
            if (s != e) {
                lazy[n * 2] += lazy[n];
                lazy[n * 2 + 1] += lazy[n];
            }
            lazy[n] = 0;
        }
    }
}
