import java.io.*;
import java.util.*;

public class Main {
    static int R, C, sr, sc, turn;
    static Player P;
    static F[][] map;
    static String cmd;
    static Monster[] M;
    static Box[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new F[R][C];
        int mCnt = 1, bCnt = 0;
        for (int r = 0; r < R; r++) {
            String input = br.readLine();
            for (int c = 0; c < C; c++) {
                char ch = input.charAt(c);
                if (ch == '@') {
                    sr = r;
                    sc = c;
                    ch = '.';
                    P = new Player();
                }
                if (ch == '&')
                    mCnt++;
                if (ch == 'B')
                    bCnt++;
                map[r][c] = new F(ch, -1);
            }
        }
        cmd = br.readLine();
        M = new Monster[mCnt];

        for (int m = 0; m < mCnt; m++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].i = m;
            M[m] = new Monster(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        B = new Box[bCnt];
        List<String> S = Arrays.asList("HR", "RE", "CO", "EX", "DX", "HU", "CU");
        for (int b = 0; b < bCnt; b++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].i = b;
            char t = st.nextToken().charAt(0);
            B[b] = new Box(t, t == 'O' ? S.indexOf(st.nextToken()) : Integer.parseInt(st.nextToken()));
        }

        play();

        System.out.println();
    }

    private static void play() {
        for (int i = 0; i < cmd.length(); i++) {
            turn++;
            int d = "RDLU".indexOf(cmd.charAt(i));
            int nr = P.r + dr[d];
            int nc = P.c + dc[d];
            if (isIn(nr, nc) && map[nr][nc].c != '#') {
                P.r = nr;
                P.c = nc;
            }
            switch (map[P.r][P.c].c) {
                case '.':
                    break;
                case '&':
                case 'M':
                    if (P.battle())
                        return;
                    break;
                case '^':
                    P.damage(P.O[4] ? 1 : 5, "SPIKE TRAP");
                    break;
                case 'B':
                    P.openBox();
                    break;
            }

            if (P.hpRem == 0)
                return;
        }
        print(false, null);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {1, 0, -1, 0};


    private static class Player {
        int r, c, lv = 1;
        int hpRem = 20, hpCur = 20;
        int attN = 2, attW = 0;
        int defN = 2, defA = 0;
        int expCur = 0, expMax = 5;
        boolean[] O = new boolean[7];
        int oCnt = 0;

        public Player() {
            r = sr;
            c = sc;
        }


        public boolean battle() {
            Monster m = M[map[r][c].i];
            boolean isBoss = map[r][c].c == 'M';
            int bw = O[2] ? O[4] ? 3 : 2 : 1;
            int ba = 1;
            int mh = m.h;
            if (O[5] && isBoss) {
                hpRem = hpCur;
                ba = 0;
            }
            while (true) {
                mh -= Math.max(1, (attN + attW) * bw - m.a);
                bw = 1;
                if (mh <= 0) {
                    getExp(m.e);
                    if (O[0])
                        hpRem = Math.min(hpRem + 3, hpCur);
                    if (isBoss) {
                        print(true, null);
                        return true;
                    }
                    map[r][c].c = '.';
                    return false;
                }
                if (!damage(Math.max(1, m.w - (defN + defA)) * ba, m.n))
                    return false;
                ba = 1;
            }
        }

        private void getExp(int e) {
            if (O[3])
                e = (int) (e * 1.2);
            expCur += e;
            if (expMax <= expCur) {
                lv++;
                hpCur += 5;
                hpRem = hpCur;
                attN += 2;
                defN += 2;
                expCur = 0;
                expMax = lv * 5;
            }
        }

        public boolean damage(int n, String attacker) {
            hpRem = Math.max(0, hpRem - n);
            if (hpRem == 0) {
                if (O[1]) {
                    O[1] = false;
                    oCnt--;
                    r = sr;
                    c = sc;
                    hpRem = hpCur;
                    return false;
                }
                print(true, attacker);
                return false;
            }
            return true;
        }

        public void openBox() {
            map[r][c].c = '.';
            Box box = B[map[r][c].i];
            if (box.t == 'W')
                attW = box.v;
            else if (box.t == 'A')
                defA = box.v;
            else {
                if (oCnt >= 4 || O[box.v])
                    return;
                O[box.v] = true;
                oCnt++;
            }
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append(String.format("LV : %s\n", lv))
                    .append(String.format("HP : %s/%s\n", hpRem, hpCur))
                    .append(String.format("ATT : %s+%s\n", attN, attW))
                    .append(String.format("DEF : %s+%s\n", defN, defA))
                    .append(String.format("EXP : %s/%s\n", expCur, expMax))
                    .toString();
        }
    }


    private static void print(boolean clear, String attacker) {
        StringBuilder sb = new StringBuilder();
        if (attacker == null)
            map[P.r][P.c].c = '@';
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++)
                sb.append(map[r][c].c);
            sb.append("\n");
        }
        sb.append("Passed Turns : ").append(turn).append("\n");
        sb.append(P);
        if (attacker != null)
            sb.append("YOU HAVE BEEN KILLED BY ").append(attacker).append("..");
        else if (!clear)
            sb.append("Press any key to continue.");
        else
            sb.append("YOU WIN!");
        System.out.println(sb);
    }

    private static class F {
        char c;
        int i;

        public F(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }

    private static class Monster {
        String n;
        int w, a, h, e;

        public Monster(String n, int w, int a, int h, int e) {
            this.n = n;
            this.w = w;
            this.a = a;
            this.h = h;
            this.e = e;
        }
    }

    private static class Box {
        char t;
        int v;

        public Box(char t, int v) {
            this.t = t;
            this.v = v;
        }
    }
}