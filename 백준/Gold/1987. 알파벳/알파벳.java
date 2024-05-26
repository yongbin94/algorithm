import java.io.*;
import java.util.*;

public class Main {
    static char[][] A;
    static boolean[] visited;
    static int N, M;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++)
            A[i] = br.readLine().toCharArray();

        visited = new boolean[26];
        answer = 0;
        
        dfs(0, 0, 1);
        
        System.out.println(answer);
    }

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static void dfs(int y, int x, int count) {
        visited[A[y][x] - 'A'] = true;
        if(answer < count)
            answer = count;
        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny < 0 || nx < 0 || ny >= N || nx >= M)
                continue;
            if(!visited[A[ny][nx] - 'A'])
                dfs(ny, nx, count + 1);
        }
        visited[A[y][x] - 'A'] = false;
    }
}
