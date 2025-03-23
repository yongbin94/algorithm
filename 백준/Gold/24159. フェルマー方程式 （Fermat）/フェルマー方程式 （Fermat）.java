import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int n = sc.nextInt();

        int[] A = new int[p];
        for (int i = 0; i < p; i++) {
            A[i] = pow(i, n, p);
        }

        int[] count = new int[p];
        for (int x = 0; x < p; x++) {
            for (int y = 0; y < p; y++) {
                count[(A[x] + A[y]) % p]++;
            }
        }

        long answer = 0;
        for (int z = 0; z < p; z++) {
            answer += count[A[z]];
        }

        System.out.println(answer);
    }

    private static int pow(long i, int n, int p) {
        long result = 1;
        while (n > 0) {
            if ((n & 1) == 1) result = (result * i) % p;
            i = (i * i) % p;
            n >>= 1;
        }
        return (int) result;
    }
}