import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Line> list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            list.add(new Line(new StringTokenizer(br.readLine())));
        }
        Collections.sort(list);

        SegmentTree seg = new SegmentTree();
        int res = Integer.MAX_VALUE;
        for (Line line : list) {
            int v = seg.query(1, line.b - 1) + 1;
            seg.update(line.b, v);
            res = Math.min(res, N - v);
        }
        System.out.println(res);

    }

    static int max = 0;

    private static class Line implements Comparable<Line> {
        int a, b;

        public Line(StringTokenizer st) {
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            max = Math.max(max, b);
        }

        @Override
        public int compareTo(Line o) {
            return this.a != o.a ? this.a - o.a : this.b - o.b;
        }
    }

    private static class SegmentTree {
        int startIndex;
        int[] tree;

        public SegmentTree() {
            startIndex = 1;
            while (startIndex < max) startIndex <<= 1;
            tree = new int[startIndex * 2];
        }

        void update(int i, int v) {
            i += startIndex - 1;
            tree[i] = Math.max(tree[i], v);
            while (i > 1) {
                i >>= 1;
                tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
            }
        }

        int query(int l, int r) {
            if (l > r) return 0;
            l += startIndex - 1;
            r += startIndex - 1;
            int res = 0;
            while (l <= r) {
                if ((l & 1) == 1) res = Math.max(res, tree[l++]);
                if ((r & 1) == 0) res = Math.max(res, tree[r--]);
                l >>= 1;
                r >>= 1;
            }
            return res;
        }
    }
}