public class Main {
    public static void main(String[] args) {
        int N = new java.util.Scanner(System.in).nextInt();
        if (N % 2 == 1 || N == 0) {
            System.out.println(0);
            return;
        }
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[2] = 3;
        for (int n = 4; n <= N; n += 2)
            dp[n] = dp[n - 2] * 4 - dp[n - 4];
        System.out.println(dp[N]);
    }
}