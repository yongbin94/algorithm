public class Main {
    static int N;
    static int[] A;
    static boolean[] selected;

    public static void main(String[] args) {
        N = new java.util.Scanner(System.in).nextInt();
        A = new int[7];
        selected = new boolean[10];
        if (!recur(0)) System.out.println("No Answer");
    }

    private static boolean recur(int i) {
        if (i == 7) {
            int a = (((A[2] * 10 + A[1]) * 10 + A[3]) * 10 + A[3]) * 10 + A[4];
            int b = (((A[6] * 10 + A[4]) * 10 + A[5]) * 10 + A[3]) * 10 + A[0];
            if (a + b != N) return false;
            System.out.printf("  %d\n+ %d\n-------\n %6d\n", a, b, a + b);
            return true;
        }
        for (int n = (i == 2 || i == 6) ? 1 : 0; n < 10; n++) {
            if (selected[n]) continue;
            A[i] = n;
            selected[n] = true;
            if (recur(i + 1)) return true;
            selected[n] = false;
        }
        return false;
    }
}