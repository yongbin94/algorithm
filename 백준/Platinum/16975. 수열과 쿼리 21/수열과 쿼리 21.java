import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        FenwickTree ft = new FenwickTree(N);
        ft.init(new StringTokenizer(br.readLine()));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1)
                ft.update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            else sb.append(ft.query(Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }


    private static class FenwickTree {
        int N;
        long[] bit;

        public FenwickTree(int n) {
            N = n;
            bit = new long[N + 2];
        }

        public void init(StringTokenizer st) {
            long prev = 0;
            for (int n = 1; n <= N; n++) {
                long curr = Integer.parseInt(st.nextToken());
                add(n, curr - prev);
                prev = curr;
            }
        }

        public void update(int l, int r, long v) {
            add(l, v);
            add(r + 1, -v);
        }

        public long query(int i) {
            return sum(i);
        }

        private void add(int i, long v) {
            while (i <= N) {
                bit[i] += v;
                i += i & -i;
            }
        }

        private long sum(int i) {
            long res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & -i;
            }
            return res;
        }
    }
}