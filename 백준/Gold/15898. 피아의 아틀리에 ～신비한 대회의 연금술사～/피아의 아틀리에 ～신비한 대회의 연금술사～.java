import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static Item[] items;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        items = new Item[N];
        for (int n = 0; n < N; n++) {
            items[n] = new Item(br);
        }
        used = new boolean[N];
        recur(new Kiln(), 0);
        System.out.println(answer);
    }

    private static void recur(Kiln kiln, int count) {
        if (count == 3) {
            answer = Math.max(answer, kiln.calc());
            return;
        }
        for (int n = 0; n < N; n++) {
            if (used[n]) continue;
            used[n] = true;
            for (int d = 0; d < 4; d++) {
                for (int p = 0; p < 4; p++) {
                    recur(new Kiln(kiln).put(items[n], d, p), count + 1);
                }
            }
            used[n] = false;
        }
    }

    private static class Kiln {
        int[][] eff;
        char[][] ele;

        public Kiln() {
            eff = new int[5][5];
            ele = new char[5][5];
        }

        public Kiln(Kiln o) {
            this();
            for (int n = 0; n < 5; n++) {
                eff[n] = Arrays.copyOf(o.eff[n], 5);
                ele[n] = Arrays.copyOf(o.ele[n], 5);
            }
        }

        public Kiln put(Item item, int d, int p) {
            int pr = p / 2;
            int pc = p % 2;
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    int nr, nc;
                    if (d == 0) {
                        nr = r;
                        nc = c;
                    } else if (d == 1) {
                        nr = c;
                        nc = 3 - r;
                    } else if (d == 2) {
                        nr = 3 - r;
                        nc = 3 - c;
                    } else {
                        nr = 3 - c;
                        nc = r;
                    }
                    eff[r + pr][c + pc] = Math.min(9, Math.max(0, eff[r + pr][c + pc] + item.eff[nr][nc]));
                    if (item.ele[nr][nc] != 'W') ele[r + pr][c + pc] = item.ele[nr][nc];
                }
            }
            return this;
        }

        public int calc() {
            int res = 0;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (ele[r][c] == 'R') res += eff[r][c] * 7;
                    else if (ele[r][c] == 'B') res += eff[r][c] * 5;
                    else if (ele[r][c] == 'G') res += eff[r][c] * 3;
                    else if (ele[r][c] == 'Y') res += eff[r][c] * 2;
                }
            }
            return res;
        }
    }

    private static class Item {
        int[][] eff;
        char[][] ele;

        public Item(BufferedReader br) throws IOException {
            eff = new int[4][4];
            ele = new char[4][4];
            for (int n = 0; n < 8; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int m = 0; m < 4; m++) {
                    if (n < 4) eff[n][m] = Integer.parseInt(st.nextToken());
                    else ele[n % 4][m] = st.nextToken().charAt(0);
                }
            }
        }
    }
}