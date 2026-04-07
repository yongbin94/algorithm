import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dp[Integer.parseInt(st.nextToken())] = 1;
        }

        for (int i = 1; i <= 100000; i++) {
            if (dp[i] == 0) continue;
            for (int j = i * 2; j <= 100000; j += i) {
                dp[j] += dp[i];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(dp[Integer.parseInt(st.nextToken())]).append(" ");
        }
        
        System.out.println(sb);
    }
}