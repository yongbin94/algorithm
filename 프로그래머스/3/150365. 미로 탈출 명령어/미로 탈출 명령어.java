import java.util.*;
import java.awt.*;
class Solution {
    static int N, M, R, C, K, X, Y;
    static char[] cArr;
    static int[][] visited;
    static StringBuilder sb = new StringBuilder();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r;
        C = c;
        K = k;
        if(((Math.abs(x - R) + Math.abs(y - C) + K) % 2) == 0
          && (Math.abs(x - R) + Math.abs(y - C)) <= K ) {
            cArr = new char[K];
            visited = new int[N + 1][M + 1];
            Arrays.stream(visited).forEach(v -> Arrays.fill(v, -1));
            dfs(x, y, 0);
            bfs();
        }
        return sb.toString().equals("") || sb.length() != K ? "impossible" : sb.toString();
    }
    
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] out = {'d', 'l', 'r', 'u'};
    
    private static boolean dfs(int x, int y, int cnt) {
        if(Math.abs(x - R) + Math.abs(y - C) == K - cnt) {
            for(int i = 0; i < cnt; i++)
                sb.append(cArr[i]);
            X = x;
            Y = y;
            return true;
        }
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!isIn(nx, ny))
                continue;
            cArr[cnt] = out[d];
            if(dfs(nx, ny, cnt + 1))
                return true;
        }
        return false;
    }
    
    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(X, Y));
        visited[X][Y] = 5;
        int count = 0;
        outer : while(!q.isEmpty()) {
            for(int i = 0, size = q.size(); i < size; i++) {
                Point p = q.poll();
                if(p.x == R && p.y == C)
                    break outer;
                for(int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(!isIn(nx, ny) || visited[nx][ny] != -1)
                        continue;
                    visited[nx][ny] = d;
                    q.offer(new Point(nx,ny));
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        while(R != X || C != Y) {
            int d = visited[R][C];
            stack.push(d);
            R -= dx[d];
            C -= dy[d];
        }
        while(!stack.isEmpty())
            sb.append(out[stack.pop()]);
    }
    
    static boolean isIn(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }
}