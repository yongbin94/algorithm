import java.io.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            recur(1, "");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void recur(int n, String s) {
        if (n == N) {
            calc(s);
            return;
        }
        recur(n + 1, s + " ");
        recur(n + 1, s + "+");
        recur(n + 1, s + "-");
    }

    private static void calc(String s) {
        int sum = 0;
        int prev = 1;
        for (int n = 2; n <= N; n++) {
            if (s.charAt(n - 2) == '+') {
                sum += prev;
                prev = n;
            } else if (s.charAt(n - 2) == '-') {
                sum += prev;
                prev = -n;
            } else {
                prev *= 10;
                prev += prev > 0 ? n : -n;
            }
        }
        sum += prev;
        if (sum == 0) {
            sb.append(1);
            for (int n = 2; n <= N; n++) {
                sb.append(s.charAt(n - 2)).append(n);
            }
            sb.append("\n");
        }
    }
}