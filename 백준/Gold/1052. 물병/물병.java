import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt(), i = 0, res = 0;
        while (Integer.bitCount(N) > K) {
            int v = 1 << i++;
            if ((v & N) == 0) continue;
            N += v;
            res += v;
        }
        System.out.println(res);
    }
}
