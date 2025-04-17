import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] hVisited;
    static BitSet[][] pVisited;
    static Queue<Hanbyeol> H;
    static Queue<Person> P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        hVisited = new boolean[N][M];
        pVisited = new BitSet[N][M];
        H = new ArrayDeque<>();
        P = new ArrayDeque<>();

        int cnt = 0;
        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().toCharArray();
            for (int m = 0; m < M; m++) {
                pVisited[n][m] = new BitSet();
                if (map[n][m] == 'H') {
                    hVisited[n][m] = true;
                    H.offer(new Hanbyeol(n, m, pVisited[n][m]));
                } else if (map[n][m] == 'P') {
                    pVisited[n][m].set(cnt);
                    P.offer(new Person(n, m, cnt++));
                }
            }
        }

        System.out.println(solution());
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int solution() {
        int res = -1;
        while (!H.isEmpty()) {
            for (int i = 0, size = P.size(); i < size; i++) {
                Person p = P.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] == 'X' || pVisited[nr][nc].get(p.idx)) continue;
                    pVisited[nr][nc].set(p.idx);
                    P.offer(new Person(nr, nc, p.idx));
                }
            }
            for (int i = 0, size = H.size(); i < size; i++) {
                Hanbyeol h = H.poll();
                if (map[h.r][h.c] == '#') {
                    res = Math.max(res, h.bs.cardinality());
                }
                for (int d = 0; d < 4; d++) {
                    int nr = h.r + dr[d];
                    int nc = h.c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] == 'X' || hVisited[nr][nc]) continue;
                    hVisited[nr][nc] = true;
                    H.offer(new Hanbyeol(nr, nc, h.bs));
                }
            }
        }
        return res;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Hanbyeol {
        int r, c;
        BitSet bs;

        public Hanbyeol(int r, int c, BitSet bs) {
            this.r = r;
            this.c = c;
            this.bs = (BitSet) bs.clone();
            this.bs.or(pVisited[r][c]);
        }
    }

    private static class Person {
        int r, c, idx;

        public Person(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }
}