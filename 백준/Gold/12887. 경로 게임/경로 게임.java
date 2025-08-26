import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = N * 2;
        boolean[][] A = new boolean[2][N];
        String a = br.readLine();
        String b = br.readLine();
        for (int n = 0; n < N; n++) {
            if (a.charAt(n) == '#') {
                A[0][n] = true;
                K--;
            }
            if (b.charAt(n) == '#') {
                A[1][n] = true;
                K--;
            }
        }
        Queue<Pos> q = new ArrayDeque<>();
        if (!A[0][0]) {
            q.offer(new Pos(0, 0));
            A[0][0] = true;
        }
        if (!A[1][0]) {
            q.offer(new Pos(1, 0));
            A[1][0] = true;
        }
        int k = 1;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                int nr = (p.r + 1) % 2;
                int nc = p.c + 1;
                if (nc == N) {
                    System.out.println(K - k);
                    return;
                }
                if (!A[nr][p.c]) {
                    q.offer(new Pos(nr, p.c));
                    A[nr][p.c] = true;
                }
                if (!A[p.r][nc]) {
                    q.offer(new Pos(p.r, nc));
                    A[p.r][nc] = true;
                }
            }
            k++;
        }
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}