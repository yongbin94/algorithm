import java.io.*;
import java.util.*;

public class Main {
    static int[][] input;
    static int[][] map;
    static int[] row, col, sq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new int[9][];
        map = new int[9][9];
        for (int n = 0; n < 9; n++)
            input[n] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        row = new int[9];
        col = new int[9];
        sq = new int[9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int v = input[r][c];
                if (input[r][c] == 0)
                    continue;
                int bit = 1 << v;
                row[r] += bit;
                col[c] += bit;
                sq[r / 3 * 3 + c / 3] += bit;
            }
        }

        Queue<Pos> q = new ArrayDeque<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (input[r][c] != 0)
                    continue;
                int bit = sq[r / 3 * 3 + c / 3] | row[r] | col[c];
                map[r][c] = bit;
                if (Integer.bitCount(bit) == 8)
                    q.offer(new Pos(r, c));
            }
        }

        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (input[p.r][p.c] != 0)
                continue;
            int number = 0;
            while (true)
                if (((~map[p.r][p.c] >> ++number) & 1) == 1)
                    break;

            input[p.r][p.c] = number;
            int bit = 1 << number;
            row[p.r] += bit;
            col[p.c] += bit;
            sq[p.r / 3 * 3 + p.c / 3] += bit;

            int sqR = p.r / 3 * 3;
            int sqC = p.c / 3 * 3;
            for (int i = 0; i < 9; i++) {
                if (input[p.r][i] == 0) {
                    map[p.r][i] |= bit;
                    if (Integer.bitCount(map[p.r][i]) == 8)
                        q.offer(new Pos(p.r, i));
                }
                if (input[i][p.c] == 0) {
                    map[i][p.c] |= bit;
                    if (Integer.bitCount(map[i][p.c]) == 8)
                        q.offer(new Pos(i, p.c));
                }
                if (input[sqR][sqC] == 0) {
                    map[sqR][sqC] |= bit;
                    if (Integer.bitCount(map[sqR][sqC]) == 8)
                        q.offer(new Pos(sqR, sqC));
                }
                if (++sqC % 3 == 0) {
                    sqC -= 3;
                    sqR++;
                }
            }

        }

        recur(0);
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++)
                sb.append(input[r][c]);
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static boolean recur(int cnt) {
        if (cnt == 81) {
            return true;
        }
        int r = cnt / 9;
        int c = cnt % 9;
        if (input[r][c] != 0) {
            return recur(cnt + 1);
        }

        int bit = row[r] | col[c] | sq[r / 3 * 3 + c / 3];
        for (int i = 1; i <= 9; i++) {
            if (((bit >> i) & 1) == 1)
                continue;
            int number = 1 << i;
            row[r] += number;
            col[c] += number;
            sq[r / 3 * 3 + c / 3] += number;
            input[r][c] = i;
            if(recur(cnt + 1))
                return true;
            input[r][c] = 0;
            row[r] -= number;
            col[c] -= number;
            sq[r / 3 * 3 + c / 3] -= number;
        }
        return false;
    }

    public static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}