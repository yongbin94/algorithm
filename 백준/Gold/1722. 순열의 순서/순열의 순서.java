import java.io.*;
import java.util.*;

public class Main {
    static long[] dp;
    static int N;
    static int[] nums;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int problem = Integer.parseInt(st.nextToken());

        if (problem == 1) {
            long K = Long.parseLong(st.nextToken());
            solution1(K);
        } else {
            nums = new int[N];
            for (int i = 0; i < N; i++)
                nums[i] = Integer.parseInt(st.nextToken());
            System.out.println(solution2());
        }
    }

    static void solution1(long K) {
        used = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (used[j]) continue;
                if (dp[N - i - 1] < K) {
                    K -= dp[N - i - 1];
                } else {
                    sb.append(j).append(" ");
                    used[j] = true;
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    static long solution2() {
        used = new boolean[N + 1];
        long order = 1;

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 1; j < nums[i]; j++) {
                if (!used[j]) count++;
            }
            order += count * dp[N - i - 1];
            used[nums[i]] = true;
        }

        return order;
    }
}