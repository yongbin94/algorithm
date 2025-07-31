import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[][] map = new long[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                map[x][y] = Long.parseLong(st.nextToken());
            }
        }
        FenwickTree2D ft = new FenwickTree2D(N, map);
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            if (w == 0)
                ft.update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            else
                sb.append(ft.query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    private static class FenwickTree2D {
        int N;
        long[][] bit, map;

        public FenwickTree2D(int n, long[][] map) {
            N = n;
            bit = new long[N + 1][N + 1];
            this.map = map;
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    add(x, y, map[x][y]);
                }
            }
        }

        public void update(int x, int y, long v) {
            add(x, y, v - map[x][y]);
            map[x][y] = v;
        }

        public long query(int x1, int y1, int x2, int y2) {
            return sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1);
        }


        private void add(int x, int y, long v) {
            for (int i = x; i <= N; i += i & -i) {
                for (int j = y; j <= N; j += j & -j) {
                    bit[i][j] += v;
                }
            }
        }

        private long sum(int x, int y) {
            long res = 0;
            for (int i = x; i > 0; i -= i & -i) {
                for (int j = y; j > 0; j -= j & -j) {
                    res += bit[i][j];
                }
            }
            return res;
        }

    }
}