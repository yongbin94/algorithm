import java.io.*;
import java.util.*;

public class Main {
    static int[] map = {2, 2, 2, 3, 3, 3, 4, 4, 1, 1, 5, 5, 6, 6, 0, 7, 0, 7, 7, 8, 8, 8, 9, 9, 9, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        String[] A = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (char c : words[i].toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    sb.append(map[c - 'a']);
                }
            }
            A[i] = sb.toString();
        }

        int len = S.length();
        int[] dp = new int[len + 1];
        int[] prev = new int[len + 1];
        int[] used = new int[len + 1];
        
        Arrays.fill(dp, 101);
        dp[0] = 0;

        for (int i = 0; i < len; i++) {
            if (dp[i] == 101) continue;

            for (int j = 0; j < n; j++) {
                String w = A[j];
                if (i + w.length() <= len && S.substring(i, i + w.length()).equals(w)) {
                    if (dp[i + w.length()] > dp[i] + 1) {
                        dp[i + w.length()] = dp[i] + 1;
                        prev[i + w.length()] = i;
                        used[i + w.length()] = j;
                    }
                }
            }
        }

        if (dp[len] == 101) {
            System.out.println("0\nNo solution.");
        } else {
            System.out.println(dp[len]);
            Deque<Integer> res = new ArrayDeque<>();
            int curr = len;
            while (curr > 0) {
                res.push(used[curr]);
                curr = prev[curr];
            }
            while (!res.isEmpty()) {
                System.out.println(words[res.pop()]);
            }
        }
    }
}