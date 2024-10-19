import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] map;
    static int[][] A;
    static int[] D;
    static Piece[] pieces;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        pieces = new Piece[K + 1];
        D = new int[K + 1];
        A = new int[N][N];
        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            D[k] = d;
            List<Integer> list = new ArrayList<>();
            list.add(k);
            pieces[k] = new Piece(r, c, list);
            A[r][c] = k;
        }
        solution();
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    private static void solution() {
        int time = 0;
        while (time++ < 1000) {
            for (int k = 1; k <= K; k++) {
                Piece p = pieces[k];
                if (p.list.get(0) != k)
                    continue;
                int nr = p.r + dr[D[k]];
                int nc = p.c + dc[D[k]];
                if (!isIn(nr, nc) || map[nr][nc] == 2) {
                    D[k] = D[k] % 2 == 0 ? D[k] + 1 : D[k] - 1;
                    nr = p.r + dr[D[k]];
                    nc = p.c + dc[D[k]];
                    if (!isIn(nr, nc) || map[nr][nc] == 2)
                        continue;
                }
                A[p.r][p.c] = 0;
                if(map[nr][nc] == 1)
                    Collections.reverse(p.list);
                if (A[nr][nc] != 0) {
                    pieces[A[nr][nc]].list.addAll(p.list);
                    for (int idx : p.list)
                        pieces[idx] = pieces[A[nr][nc]];
                    if (pieces[A[nr][nc]].list.size() >= 4) {
                        System.out.println(time);
                        return;
                    }
                    continue;
                }
                p.r = nr;
                p.c = nc;
                A[nr][nc] = k;
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Piece {
        int r, c;
        List<Integer> list;

        public Piece(int r, int c, List<Integer> list) {
            this.r = r;
            this.c = c;
            this.list = list;
        }
    }
}