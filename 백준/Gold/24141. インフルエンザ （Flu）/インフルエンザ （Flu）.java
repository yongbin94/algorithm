import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D, K, answer;
    static boolean[][] map, visited;
    static Queue<Pos> F;
    static List<Pos> P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        D = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new boolean[1000][1000];
        visited = new boolean[1000][1000];
        F = new ArrayDeque<>();
        P = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Pos p = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        F.offer(p);
        map[p.r][p.c] = true;
        visited[p.r][p.c] = true;
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        getPosList();
        solution();
        System.out.println(answer);
    }

    private static void getPosList() {
        for (int r = -D; r <= D; r++) {
            for (int c = -D; c <= D; c++) {
                if (r * r + c * c <= D * D) {
                    P.add(new Pos(r, c));
                }
            }
        }
    }

    private static void solution() {
        for (int k = 0; k <= K; k++) {
            if (K - k < M) answer += F.size();
            for (int i = 0, size = F.size(); i < size; i++) {
                Pos flu = F.poll();
                for (Pos p : P) {
                    int nr = flu.r + p.r;
                    int nc = flu.c + p.c;
                    if (!isIn(nr, nc) || visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    if (map[nr][nc]) F.offer(new Pos(nr, nc));
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < 1000 && c >= 0 && c < 1000;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
}