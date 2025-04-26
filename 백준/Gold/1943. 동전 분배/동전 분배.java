import java.io.*;
import java.util.*;

public class Main {
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 3; t++) {
            int N = Integer.parseInt(br.readLine());
            total = 0;
            Coin[] coins = new Coin[N];
            for (int n = 0; n < N; n++) {
                coins[n] = new Coin(new StringTokenizer(br.readLine()));
            }
            if (total % 2 != 0) {
                sb.append("0").append("\n");
                continue;
            }
            int max = total / 2;
            boolean[] used = new boolean[max + 1];
            boolean[] dp = new boolean[max + 1];
            dp[0] = true;
            for (Coin coin : coins) {
                for (int i = max; i >= 0; i--) {
                    if (!dp[i]) continue;
                    for (int j = 1; j <= coin.count; j++) {
                        int v = i + coin.value * j;
                        if (v > max || used[v]) break;
                        used[v] = true;
                        dp[v] = true;
                    }
                }
            }
            sb.append(dp[total / 2] ? "1" : "0").append("\n");
        }
        System.out.println(sb);
    }

    private static class Coin {
        int value, count;

        public Coin(StringTokenizer st) {
            value = Integer.parseInt(st.nextToken());
            count = Integer.parseInt(st.nextToken());
            total += value * count;
        }
    }
}