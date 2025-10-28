import java.util.*;

public class Main {
    static final int MOD = 1_000_000;

    public static void main(String[] args) {
        String S = new Scanner(System.in).nextLine();
        if (S.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int N = S.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        int prev = S.charAt(0) - '0';
        for (int i = 2; i <= N; i++) {
            int curr = S.charAt(i - 1) - '0';
            if (curr != 0) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }
            int t = prev * 10 + curr;
            if (t >= 10 && t <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
            prev = curr;
        }
        System.out.println(dp[N]);
    }
}