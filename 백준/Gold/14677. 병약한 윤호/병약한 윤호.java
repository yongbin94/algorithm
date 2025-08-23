import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) * 3;
        String S = br.readLine();
        char[] A = {'B', 'L', 'D'};
        int[][] dp = new int[N][N];
        for (int i = 1; i <= N; i++) {
            int k = (N - i) % 3;
            for (int l = 0; l + i - 1 < N; l++) {
                int r = l + i - 1;
                int max = 0;
                if (S.charAt(l) == A[k]) {
                    max = Math.max(max, 1 + (l + 1 <= r ? dp[l + 1][r] : 0));
                }
                if (S.charAt(r) == A[k]) {
                    max = Math.max(max, 1 + (l <= r - 1 ? dp[l][r - 1] : 0));
                }
                dp[l][r] = max;
            }
        }
        System.out.println(dp[0][N - 1]);
    }
}