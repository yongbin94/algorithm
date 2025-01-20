import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, W;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int r = N - 1; r >= 0; r--) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int v = Integer.parseInt(st.nextToken());
                H = Math.max(H, r * 2 + v * 3 + 3);
                pq.offer(new Info(r, c, v));
            }
        }
        W = M * 4 + N * 2 + 1;
        map = new char[H][W];
        for (int h = 0; h < H; h++)
            Arrays.fill(map[h], '.');
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            draw(info);
            if (--info.h > 0)
                pq.offer(info);
        }
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int h = H - 1; h >= 0; h--) {
            for (int w = 0; w < W; w++)
                sb.append(map[h][w]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static String[] S = {"+---+", "|   |/", "|   | +", "+---+ |", " /   /|", "  +---+"};
    static int[] A = {0, 0, 0, 0, 1, 2};
    static int[] B = {5, 6, 7, 7, 7, 7};

    private static void draw(Info info) {
        for (int i = 0; i < 6; i++) {
            for (int j = A[i]; j < B[i]; j++) {
                int r = info.r * 2 + (info.h - 1) * 3 + i;
                int c = info.c * 4 + info.r * 2 + j;
                if (map[r][c] != '.')
                    continue;
                map[r][c] = S[i].charAt(j);
            }
        }
    }

    private static class Info implements Comparable<Info> {
        int r, c, h;

        public Info(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

        @Override
        public int compareTo(Info o) {
            return o.h != this.h ? o.h - this.h : this.r != o.r ? this.r - o.r : o.c - this.c;
        }
    }
}