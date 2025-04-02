import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        int t = 0, n = 1, cnt = 0;
        while (true) {
            t += n;
            if (N <= t) break;
            if (cnt++ % 2 == 1) n *= 2;
        }
        int v = ((1 << (cnt / 2)) + N - t + n - 1);
        int res = v;
        if (cnt % 2 == 0) v >>= 1;
        while (v > 0) {
            res = res << 1 | (v & 1);
            v >>= 1;
        }
        System.out.println(res);
    }
}
