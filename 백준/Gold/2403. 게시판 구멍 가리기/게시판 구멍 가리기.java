import java.io.*;
import java.util.*;

public class Main {
    static Pos[] P;
    static int sr, sc, er, ec;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        P = new Pos[N];
        sr = 30000;
        sc = 30000;
        er = -30000;
        ec = -30000;
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            P[n] = new Pos(r, c);
            sr = Math.min(sr, r);
            sc = Math.min(sc, c);
            er = Math.max(er, r);
            ec = Math.max(ec, c);
        }
        int res = binarySearch();
        System.out.printf("%d\n%d %d\n%d %d\n", res, sr, flag ? sc : ec - res, er - res, flag ? ec - res : sc);
    }

    private static int binarySearch() {
        int l = 0, r = Math.max(er - sr + 1, ec - sc + 1);
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean check(int n) {
        boolean a = true, b = true;
        for (Pos p : P) {
            if (a && !(((p.r >= sr && p.r <= sr + n) && (p.c >= sc && p.c <= sc + n))
                    || ((p.r >= er - n && p.r <= er) && (p.c >= ec - n && p.c <= ec))))
                a = false;
            if (b && !(((p.r >= sr && p.r <= sr + n) && (p.c >= ec - n && p.c <= ec))
                    || ((p.r >= er - n && p.r <= er) && (p.c >= sc && p.c <= sc + n))))
                b = false;
            if (!a && !b) return false;
        }
        flag = a;
        return true;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}