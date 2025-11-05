import java.util.*;

public class Main {
    static long[][] dp = new long[31][31];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n;
        while ((n = sc.nextInt()) > 0) {
            sb.append(solve(n, 0)).append("\n");
        }
        System.out.println(sb);
    }

    private static long solve(int w, int h) {
        if (dp[w][h] > 0) return dp[w][h];
        if (w == 0) return dp[w][h] = 1;
        long res = 0;
        res += solve(w - 1, h + 1);
        if (h > 0) res += solve(w, h - 1);
        return dp[w][h] = res;
    }
}