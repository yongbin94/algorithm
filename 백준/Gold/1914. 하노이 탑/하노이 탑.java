import java.io.*;
import java.math.BigInteger;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(BigInteger.ONE.shiftLeft(N).subtract(BigInteger.ONE));
        if (N <= 20) {
            recur(N, 1, 2, 3);
            System.out.print(sb);
        }
    }

    static void recur(int n, int s, int t, int e) {
        if (n == 1) {
            sb.append(s).append(" ").append(e).append('\n');
            return;
        }
        recur(n - 1, s, e, t);
        sb.append(s).append(" ").append(e).append('\n');
        recur(n - 1, t, s, e);
    }
}
