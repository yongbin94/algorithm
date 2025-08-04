import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LazySegmentTree seg = new LazySegmentTree(N);
        seg.init(new StringTokenizer(br.readLine()));
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken());
            seg.updateRange(l, r, x);
        }
        System.out.println(seg.print(N, Q));
    }

    private static class LazySegmentTree {
        final int INF = 1_000_000;
        int N, time = 0;
        int[] treeMin, treeMax, lastUpdated, lazyA, lazyT;
        StringBuilder res = new StringBuilder();

        public LazySegmentTree(int n) {
            N = 1;
            while (N < n) N <<= 1;
            treeMin = new int[N * 2];
            treeMax = new int[N * 2];
            lastUpdated = new int[N * 2];
            lazyA = new int[N * 2];
            lazyT = new int[N * 2];
            Arrays.fill(treeMin, INF);
            Arrays.fill(lazyA, INF);
        }

        public void init(StringTokenizer st) {
            for (int n = N; st.hasMoreTokens(); n++) {
                int v = Integer.parseInt(st.nextToken());
                treeMin[n] = v;
                treeMax[n] = v;
            }
            for (int n = N - 1; n > 0; n--) {
                treeMin[n] = Math.min(treeMin[n * 2], treeMin[n * 2 + 1]);
                treeMax[n] = Math.max(treeMax[n * 2], treeMax[n * 2 + 1]);
            }
        }

        public String print(int n, int q) {
            query(1, 0, N - 1, 0, n - 1);
            res.append("\n");
            int[] sum = new int[q + 1];
            for (int i = N; i < N + n; i++) {
                sum[lastUpdated[i]]++;
            }
            for (int i = q - 1; i > 0; i--) {
                sum[i] += sum[i + 1];
            }
            for (int i = 1; i <= q; i++) {
                res.append(n - sum[i]).append(" ");
            }
            return res.toString();
        }

        public void updateRange(int l, int r, int v) {
            updateRange(1, 0, N - 1, l, r, v);
            time++;
        }

        private void updateRange(int n, int s, int e, int l, int r, int v) {
            push(n, s, e);
            if (s > r || e < l || treeMax[n] <= v) return;
            if (s >= l && e <= r) {
                lazyA[n] = v;
                lazyT[n] = time;
                push(n, s, e);
                return;
            }
            int mid = (s + e) >> 1;
            updateRange(n * 2, s, mid, l, r, v);
            updateRange(n * 2 + 1, mid + 1, e, l, r, v);
            treeMin[n] = Math.min(treeMin[n * 2], treeMin[n * 2 + 1]);
            treeMax[n] = Math.max(treeMax[n * 2], treeMax[n * 2 + 1]);
        }

        private void query(int n, int s, int e, int l, int r) {
            push(n, s, e);
            if (s > r || e < l) return;
            if (s == e) {
                res.append(treeMin[n]).append(" ");
                return;
            }
            int mid = (s + e) >> 1;
            query(n * 2, s, mid, l, r);
            query(n * 2 + 1, mid + 1, e, l, r);
        }

        private void push(int n, int s, int e) {
            if (lazyA[n] == INF) return;
            if (treeMin[n] > lazyA[n]) {
                treeMin[n] = lazyA[n];
                lastUpdated[n] = lazyT[n];
            }
            treeMax[n] = Math.min(treeMax[n], lazyA[n]);
            if (s != e) {
                if (lazyA[n] < lazyA[n * 2]) {
                    lazyA[n * 2] = lazyA[n];
                    lazyT[n * 2] = lazyT[n];
                }
                if (lazyA[n] < lazyA[n * 2 + 1]) {
                    lazyA[n * 2 + 1] = lazyA[n];
                    lazyT[n * 2 + 1] = lazyT[n];
                }
            }
            lazyA[n] = INF;
        }
    }
}