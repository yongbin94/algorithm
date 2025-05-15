import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N + 2][M];
        Arrays.fill(map[0], '#');
        Arrays.fill(map[N + 1], '#');
        dp = new int[N + 2][M];
        Arrays.stream(dp).forEach(v -> Arrays.fill(v, -1));
        for (int n = 1; n <= N; n++) {
            map[n] = br.readLine().toCharArray();
            if (map[n][0] == 'R') dp[n][0] = 0;
        }
        System.out.println(solution());
    }

    private static int solution() {
        int res = -1;
        for (int m = 1; m < M; m++) {
            for (int n = 1; n <= N; n++) {
                switch (map[n][m]) {
                    case 'R':
                        dp[n][m] = 0;
                    case '#':
                        break;
                    case '.':
                    case 'C':
                    case 'O':
                        dp[n][m] = Math.max(dp[n][m - 1], Math.max(dp[n + 1][m - 1], dp[n - 1][m - 1]));
                        if (map[n][m] == 'C' && dp[n][m] >= 0) dp[n][m]++;
                        if (map[n][m] == 'O') res = Math.max(res, dp[n][m]);
                }
            }
        }
        return res;
    }
}