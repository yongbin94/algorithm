import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N];
            for (int i = 0; i < N; i++)
                A[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;
            for (int a : A)
                for (int i = a; i <= M; i++)
                    dp[i] += dp[i - a];
            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}