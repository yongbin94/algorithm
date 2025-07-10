import java.io.*;

public class Main {
    static String[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        int N = 1000;
        dp = new String[N + 1];
        dp[1] = "()";
        dp[2] = "{}";
        dp[3] = "[]";
        for (int n = 4; n <= N; n++) {
            dp[n] = dp[1] + dp[n - 1];
            for (int i = 2; i < n; i++) {
                dp[n] = min(dp[n], dp[i] + dp[n - i]);
            }
            if (n % 2 == 0) dp[n] = min(dp[n], "(" + dp[n / 2] + ")");
            if (n % 3 == 0) dp[n] = min(dp[n], "{" + dp[n / 3] + "}");
            if (n % 5 == 0) dp[n] = min(dp[n], "[" + dp[n / 5] + "]");
        }
    }

    static String order = "(){}[]";
    private static String min(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen == bLen) {
            for (int i = 0; i < aLen; i++) {
                if (a.charAt(i) == b.charAt(i)) continue;
                return order.indexOf(a.charAt(i)) < order.indexOf(b.charAt(i)) ? a : b;
            }
        }
        return aLen < bLen ? a : b;
    }
}