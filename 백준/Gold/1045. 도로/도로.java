import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static char[][] map;
    static boolean[][] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        used = new boolean[N][N];
        map = new char[N][];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
        solution();

        if (M > 0) {
            System.out.println(-1);
            return;
        }

        for(int i = 1; i < N; i++) {
            if(union(0,i)) {
                System.out.println(-1);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int n = 0; n < N; n++) {
            int cnt = 0;
            for(int m = 0; m < N;m++)
                cnt += used[n][m] ? 1 : 0;
            sb.append(cnt).append(" ");
        }
        System.out.println(sb);
    }

    private static void solution() {
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map[i][j] == 'Y')
                    q.offer(new Point(i, j));
            }
        }
        for (int i = 0, size = q.size(); i < size; i++) {
            Point p = q.poll();
            if (union(p.x, p.y)) {
                used[p.x][p.y] = true;
                used[p.y][p.x] = true;
                if (--M == 0)
                    return;
            } else {
                q.offer(p);
            }
        }
        while (M != 0 && !q.isEmpty()) {
            Point p = q.poll();
            used[p.x][p.y] = true;
            used[p.y][p.x] = true;
            M--;
        }
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)
            return false;
        parent[aRoot] = parent[bRoot];
        return true;
    }
}