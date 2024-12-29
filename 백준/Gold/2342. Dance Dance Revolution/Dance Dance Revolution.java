import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int[] A;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[5][5][A.length];
        for (int n = 0; n < 5; n++)
            for (int m = 0; m < 5; m++)
                Arrays.fill(dp[n][m], INF);
        dp[0][0][0] = 0;
        for (int n = 0; n < A.length - 1; n++) {
            int target = A[n];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    int curr = dp[l][r][n];
                    if (curr == INF || (r + l != 0 && r == l)) continue;
                    dp[target][r][n + 1] = Math.min(dp[target][r][n + 1], curr + getCost(l, target));
                    dp[l][target][n + 1] = Math.min(dp[l][target][n + 1], curr + getCost(r, target));
                }
            }
        }
        int answer = INF;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                answer = Math.min(answer, dp[l][r][A.length - 1]);
            }
        }
        System.out.println(answer);
    }

    private static int getCost(int current, int target) {
        if (current == target)
            return 1;
        if (current == 0)
            return 2;
        if (current % 2 != target % 2)
            return 3;
        return 4;
    }
}