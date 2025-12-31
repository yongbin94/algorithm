import java.io.*;
import java.util.*;

public class Main {

    static Set<Long> set;
    static String S;
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            S = br.readLine();
            N = Long.parseLong(S);

            if (check()) {
                sb.append("Hello, BOJ 2023!\n");
                continue;
            }

            set = new HashSet<>();
            recur(0, 0);

            int answer = 0;
            for (int m = 1; m <= 31; m++) {
                long sum = 0;
                boolean flag = false;

                for (char ch : S.toCharArray()) {
                    int d = ch - '0';
                    if (d == 0) continue;

                    long p = 1;
                    for (int j = 0; j < m; j++) {
                        p *= d;
                        if (p > N) {
                            flag = true;
                            break;
                        }
                    }
                    sum += p;

                    if (flag || sum > N) {
                        flag = true;
                        break;
                    }
                }

                if (flag) break;
                if (set.contains(sum)) answer++;
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static boolean check() {
        for (char ch : S.toCharArray()) {
            if (ch - '0' >= 2) return false;
        }
        return true;
    }

    static void recur(int n, long total) {
        if (n == S.length()) {
            set.add(total);
            return;
        }

        long v = 0;
        for (int i = n; i < S.length(); i++) {
            v = v * 10 + (S.charAt(i) - '0');
            if (total + v > N) break;
            recur(i + 1, total + v);
        }
    }
}