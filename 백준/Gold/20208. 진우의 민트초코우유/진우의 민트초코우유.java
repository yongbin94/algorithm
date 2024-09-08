import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, answer;
    static List<Pos> L;
    static Pos A;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        L = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 1)
                    A = new Pos(r, c);
                if (v == 2)
                    L.add(new Pos(r, c));
            }
        }
        selected = new boolean[L.size()];
        recur(0, M, A);
        System.out.println(answer);
    }

    private static void recur(int cnt, int h, Pos a) {
        if (cnt > 0) {
            if (getDistance(A, a) <= h)
                answer = Math.max(answer, cnt);
            if (cnt == L.size())
                return;
        }
        for (int i = 0; i < L.size(); i++) {
            if(selected[i])
                continue;
            int distance = getDistance(L.get(i), a);
            if(distance > h)
                continue;
            selected[i] = true;
            recur(cnt + 1, h - distance + H, L.get(i));
            selected[i] = false;
        }
    }

    private static int getDistance(Pos a, Pos b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}