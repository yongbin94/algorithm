public class Main {
    static int max, min;

    public static void main(String[] args) {
        int N = new java.util.Scanner(System.in).nextInt();
        min = Integer.MAX_VALUE;
        recur(N, 0);
        System.out.printf("%d %d\n", min, max);
    }

    private static void recur(int n, int cnt) {
        for (int x = n; x > 0; x /= 10) {
            cnt += x & 1;
        }
        if (n < 10) {
            min = Math.min(min, cnt);
            max = Math.max(max, cnt);
            return;
        }
        if (n < 100) {
            recur(n % 10 + n / 10, cnt);
            return;
        }
        int l = 0;
        for (int t = 1; t <= n; t *= 10) l++;
        for (int i = 1; i < l - 1; i++) {
            for (int j = 1; j < l - i; j++) {
                int x = (int) Math.pow(10, i);
                int y = (int) Math.pow(10, j);
                int a = (n / x) / y;
                int b = (n / x) % y;
                int c = n % x;
                recur(a + b + c, cnt);
            }
        }
    }
}