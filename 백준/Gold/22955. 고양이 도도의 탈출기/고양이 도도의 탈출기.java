import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        visited = new boolean[N][M];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        PriorityQueue<Cat> pq = new PriorityQueue<>(((o1, o2) -> o1.w - o2.w));
        pq.offer(findCat());
        while (!pq.isEmpty()) {
            Cat cat = pq.poll();
            if (visited[cat.r][cat.c])
                continue;
            visited[cat.r][cat.c] = true;
            switch (map[cat.r][cat.c]) {
                case 'L':
                    if (isIn(cat.r - 1, cat.c) && !visited[cat.r - 1][cat.c] && map[cat.r - 1][cat.c] != 'D')
                        pq.offer(new Cat(cat.r - 1, cat.c, cat.w + 5));
                    break;
                case 'X':
                    int nr = cat.r;
                    while (map[nr][cat.c] == 'X')
                        nr++;
                    if (!visited[nr][cat.c])
                        pq.offer(new Cat(nr, cat.c, cat.w + 10));
                    continue;
                case 'D':
                    continue;
                case 'E':
                    System.out.println(cat.w);
                    return;
            }
            if (isIn(cat.r + 1, cat.c) && map[cat.r + 1][cat.c] == 'L' && !visited[cat.r + 1][cat.c])
                pq.offer(new Cat(cat.r + 1, cat.c, cat.w + 5));
            for (int dc = -1; dc <= 1; dc += 2) {
                if (!isIn(cat.r, cat.c + dc) || visited[cat.r][cat.c + dc])
                    continue;
                pq.offer(new Cat(cat.r, cat.c + dc, cat.w + 1));
            }
        }
        System.out.println("dodo sad");
    }


    private static Cat findCat() {
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] == 'C')
                    return new Cat(r, c, 0);
        return null;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Cat {
        int r, c, w;

        public Cat(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}
