import java.util.*;

public class Main {
    static List<Long> list = new ArrayList<>();
    static int[] A = new int[11];
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            recur(i, 0, 10);
        }
        Collections.sort(list);
        int N = new Scanner(System.in).nextInt();
        System.out.println(list.size() < N ? -1 : list.get(N - 1));
    }

    private static void recur(int i, int j, int prev) {
        if (i == j) {
            long v = 0;
            for (int a = 0; a < i; a++) {
                v *= 10;
                v += A[a];
            }
            list.add(v);
            return;
        }
        for (int a = prev - 1; a >= i - j - 1; a--) {
            A[j] = a;
            recur(i, j + 1, a);
        }
    }
}