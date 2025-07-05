import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
            int max = A[n];
            int min = A[n];
            for (int i = n; i >= 0; i--) {
                max = Math.max(max, A[i]);
                min = Math.min(min, A[i]);
                dp[n + 1] = Math.max(dp[n + 1], dp[i] + max - min);
            }
        }
        System.out.println(dp[N]);
    }
}