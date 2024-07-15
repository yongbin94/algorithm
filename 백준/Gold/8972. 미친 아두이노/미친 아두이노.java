import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Pos I;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            String tmp = br.readLine();
            for (int c = 0; c < M; c++) {
                if (tmp.charAt(c) == 'I')
                    I = new Pos(r, c);
                if (tmp.charAt(c) == 'R')
                    q.offer(new Pos(r, c));
            }
        }
        String cmd = br.readLine();
        for (int i = 0; i < cmd.length(); i++) {
            if (play(cmd.charAt(i) - '1')) {
                System.out.println("kraj " + ++i);
                return;
            }
        }
        print();
    }

    private static void print() {
        char[][] map = new char[N][M];
        for (int n = 0; n < N; n++)
            Arrays.fill(map[n], '.');
        for (int i = 0, size = q.size(); i < size; i++) {
            Pos p = q.poll();
            map[p.r][p.c] = 'R';
            q.offer(p);
        }
        map[I.r][I.c] = 'I';
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};


    private static boolean play(int a) {
        I.r += dr[a];
        I.c += dc[a];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (I.r < p.r)
                p.r--;
            else if (I.r > p.r)
                p.r++;
            if (I.c < p.c)
                p.c--;
            else if (I.c > p.c)
                p.c++;
            if (I.r == p.r && I.c == p.c)
                return true;
            pq.offer(p);
        }
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            int cnt = 0;
            while (!pq.isEmpty() && pq.peek().equals(p)) {
                cnt++;
                pq.poll();
            }
            if (cnt == 0)
                q.offer(p);
        }
        return false;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            return this.r != o.r ? this.r - o.r : this.c - o.c;
        }

        public boolean equals(Pos o) {
            return this.r == o.r && this.c == o.c;
        }
    }
}
