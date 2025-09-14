import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            String C = st.nextToken();
            boolean[] dp = new boolean[A.length() + 1];
            dp[0] = true;
            for (int i = 0; i < C.length(); i++) {
                for (int a = A.length(); a >= 0; a--) {
                    int b = i - a;
                    if (!dp[a] || b < 0) continue;
                    if (a < A.length() && A.charAt(a) == C.charAt(i)) dp[a + 1] = true;
                    if (b >= B.length()) {
                        dp[a] = false;
                        continue;
                    }
                    dp[a] = B.charAt(b) == C.charAt(i);
                }
            }
            sb.append(String.format("Data set %d: %s\n", tc, dp[A.length()] ? "yes" : "no"));
        }
        System.out.println(sb);
    }
}