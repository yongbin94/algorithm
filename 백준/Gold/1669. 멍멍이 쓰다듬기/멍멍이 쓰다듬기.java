import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = -(sc.nextLong() - sc.nextLong());
        long i = (long) Math.sqrt(n);
        long a = (i * (i + 1)) - i;
        System.out.println(n == 0 ? 0 : i * 2 - 1 + (n == a ? 0 : (n - a - 1) / i + 1));
    }
}