import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        Map<Integer, Integer> primes = new HashMap<>();
        int n = N;
        for (int i = 2; i < Math.sqrt(n); i++) {
            while (n % i == 0) {
                primes.put(i, primes.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n > 1) primes.put(n, 1);

        int l = 1, r = N, answer = N;
        while (l <= r) {
            int mid = (l + r) / 2;
            boolean flag = true;
            for (int prime : primes.keySet()) {
                int m = mid, p = prime;
                int cnt = 0;
                while (p <= m) {
                    cnt += m / p;
                    p *= prime;
                }
                if (cnt < primes.get(prime)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(answer);
    }
}