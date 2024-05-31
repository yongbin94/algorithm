import java.io.*;

public class Main {
    static int N;
    static char[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new char[N][];
        for(int n = 0; n < N; n++) {
            A[n] = br.readLine().toCharArray();
        }
        int b = 0, rgb = 0;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(A[y][x] == 'B' || A[y][x] == 'G' || A[y][x] == 'R') {
                    if(A[y][x] == 'B') b++;
                    dfs(A[y][x], y, x);
                    rgb++;
                }
            }
        }
        int rg = 0;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(A[y][x] == 'o') {
                    dfs(A[y][x], y, x);
                    rg++;
                }
            }
        }
        System.out.println(rgb + " " + (rg + b));
    }
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    private static void dfs(char c, int y, int x) {
        if(c == 'B') A[y][x] = 'x';
        else if(c == 'o') A[y][x] = '-';
        else A[y][x] = 'o';

        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N)
                continue;
            if(A[ny][nx] == c)
                dfs(c, ny, nx);
        }
    }
}
