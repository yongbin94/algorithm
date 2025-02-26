import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N + 2][M + 2];
        visited = new boolean[N + 2][M + 2];
        Arrays.stream(board).forEach(v -> Arrays.fill(v, '#'));
        for (int n = 1; n <= N; n++) {
            String x = br.readLine();
            String y = br.readLine();
            String z = br.readLine();
            for (int m = 1; m <= M; m++) {
                char ch = getBlock(x, y, z, m - 1);
                board[n][m] = ch;
            }
        }
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                if (visited[n][m] || board[n][m] == '#')
                    continue;
                if (board[n][m] == '.') {
                    if (!check1(n, m)) {
                        System.out.println("NO");
                        return;
                    }
                } else if (board[n][m] == 'c') {
                    if (!check2(n, m)) {
                        System.out.println("NO");
                        return;
                    }
                } else if (board[n][m] >= '0' && board[n][m] <= '5') {
                    if (!check3(n, m)) {
                        System.out.println("NO");
                        return;
                    }
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static char getBlock(String x, String y, String z, int m) {
        if (y.charAt(m * 3 + 1) == '.') {
            return '.';
        } else if (x.charAt(m * 3 + 2) == '.') {
            return 'a';
        } else if (z.charAt(m * 3) == '.') {
            return 'b';
        } else if (z.charAt(m * 3 + 2) == '.') {
            return 'c';
        } else if (x.charAt(m * 3) == '.') {
            return 'd';
        } else {
            return y.charAt(m * 3 + 1);
        }
    }

    private static boolean check1(int n, int l) {
        int r = l;
        while (board[n][r + 1] == '.') r++;
        for (int i = l; i <= r; i++) {
            if (board[n - 1][i] == '.' || board[n - 1][i] == 'b' || board[n - 1][i] == 'c') return false;
        }
        while (board[n][l] == '.') {
            if (board[n][l - 1] == '.' || board[n][l - 1] == 'a' || board[n][l - 1] == 'c') return false;
            if (board[n][r + 1] == '.' || board[n][r + 1] == 'b' || board[n][r + 1] == 'd') return false;
            for (int i = l; i <= r; i++) {
                if (board[n][i] != '.') return false;
                visited[n][i] = true;
            }
            n++;
        }
        for (int i = l; i <= r; i++) {
            if (board[n][i] == '.' || board[n][i] == 'a' || board[n][i] == 'd') return false;
        }
        return true;
    }

    private static boolean check2(int n, int m) {
        int l = m, r = m + 1;
        char a = 'c', b = 'b';
        while (l < r) {
            if (board[n][l] != a || board[n][r] != b) return false;
            for (int i = l + 1; i < r; i++) {
                if (board[n][i] != '.') return false;
                visited[n][i] = true;
            }
            visited[n][l] = true;
            visited[n][r] = true;
            if (board[n][l] == 'c' && board[n + 1][l--] == 'a') a = 'a';
            if (board[n][r] == 'b' && board[n + 1][r++] == 'd') b = 'd';
            if (a == 'a') l++;
            if (b == 'd') r--;
            n++;
        }
        return true;
    }

    private static boolean check3(int n, int m) {
        int cnt = 0;
        if (board[n + 1][m] == 'b' || board[n + 1][m] == 'c') cnt++;
        if (board[n][m + 1] == 'a' || board[n][m + 1] == 'c') cnt++;
        if (board[n - 1][m] == 'a' || board[n - 1][m] == 'd') cnt++;
        if (board[n][m - 1] == 'b' || board[n][m - 1] == 'd') cnt++;
        return board[n][m] - '0' == cnt;
    }
}