import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String A = br.readLine();
        String B = br.readLine();
        int s = S.length();
        int len = A.length();
        
        int[][][] dp = new int[s][2][len];

        for (int j = 0; j < len; j++) {
            if (A.charAt(j) == S.charAt(0)) dp[0][0][j] = 1;
            if (B.charAt(j) == S.charAt(0)) dp[0][1][j] = 1;
        }
        
        for (int i = 1; i < s; i++) {
            for (int j = 0; j < len; j++) {
                if (A.charAt(j) == S.charAt(i)) {
                    for (int k = 0; k < j; k++) {
                        dp[i][0][j] += dp[i - 1][1][k];
                    }
                }

                if (B.charAt(j) == S.charAt(i)) {
                    for (int k = 0; k < j; k++) {
                        dp[i][1][j] += dp[i - 1][0][k];
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < len; j++) {
            res += dp[s - 1][0][j];
            res += dp[s - 1][1][j];
        }

        System.out.println(res);
    }
}