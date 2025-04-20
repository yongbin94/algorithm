import java.io.*;
import java.util.*;

public class Main {
    static int[] R, C, S;
    static int[][] map;
    static List<Blank> B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        R = new int[9];
        C = new int[9];
        S = new int[9];
        map = new int[9][9];
        B = new ArrayList<>();
        for (int r = 0; r < 9; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 9; c++) {
                int v = Integer.parseInt(st.nextToken());
                int s = (r / 3) * 3 + c / 3;
                map[r][c] = v--;
                if (v < 0) {
                    B.add(new Blank(r, c, s));
                } else {
                    R[r] |= 1 << v;
                    C[c] |= 1 << v;
                    S[s] |= 1 << v;
                }
            }
        }
        recur(0);
    }

    private static boolean recur(int idx) {
        if (idx == B.size()) {
            print();
            return true;
        }
        Blank blank = B.get(idx);
        int bit = blank.getBit();
        for (int i = 0; i < 9; i++) {
            if (((bit >> i) & 1) == 1) continue;
            blank.fill(i);
            if (recur(idx + 1)) {
                return true;
            }
            blank.clear(i);
        }
        return false;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(map[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static class Blank {
        int r, c, s;

        public Blank(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        private int getBit() {
            return R[r] | C[c] | S[s];
        }

        private void fill(int i) {
            R[r] |= 1 << i;
            C[c] |= 1 << i;
            S[s] |= 1 << i;
            map[r][c] = i + 1;
        }

        private void clear(int i) {
            R[r] &= ~(1 << i);
            C[c] &= ~(1 << i);
            S[s] &= ~(1 << i);
        }
    }
}