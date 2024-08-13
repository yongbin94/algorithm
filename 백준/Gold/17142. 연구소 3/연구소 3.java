import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static List<Pos> list;
    static long[] map, virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new long[N];
        virus = new long[N];
        list = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                switch (st.nextToken().charAt(0)) {
                    case '1':
                        map[i] += 1L << j;
                        break;
                    case '2':
                        list.add(new Pos(i, j));
                        virus[i] += 1L << j;
                }
            }
        }
        recur(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void recur(int start, int cnt, int selected) {
        if (cnt == M)
            answer = Math.min(answer, bfs(selected));
        else
            for (int i = start; i <= list.size(); i++)
                recur(i + 1, cnt + 1, selected + (1 << i));
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static int bfs(int selected) {
        long[] tmp = Arrays.copyOf(map, N);
        Queue<Pos> q = new ArrayDeque<>();
        for (int i = 0; i < list.size(); i++) {
            if (((selected >> i) & 1) == 1) {
                Pos p = list.get(i);
                q.offer(p);
                tmp[p.r] += 1L << p.c;
            }
        }

        int time = 0;
        while (!q.isEmpty()) {

            boolean flag = true;
            for (int n = 0; n < N; n++)
                if (Long.bitCount(tmp[n] | virus[n]) != N)
                    flag = false;
            if (flag)
                break;

            time++;
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || ((tmp[nr] >> nc) & 1) == 1)
                        continue;
                    tmp[nr] += 1L << nc;
                    q.offer(new Pos(nr, nc));
                }
            }
        }

        for (int n = 0; n < N; n++)
            if (Long.bitCount(tmp[n] | virus[n]) != N)
                return Integer.MAX_VALUE;

        return time;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}