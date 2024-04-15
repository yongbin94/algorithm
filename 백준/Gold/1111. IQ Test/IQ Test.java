import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (N <= 2) {
            System.out.println(N == 2 && A[0] == A[1] ? A[0] : "A");
            return;
        }
        if ((A[1] - A[0]) != 0 && (A[2] - A[1]) % (A[1] - A[0]) != 0) {
            System.out.println("B");
            return;
        }
        int num = A[1] - A[0];
        int diff = A[0] == A[1] ? 0 : (A[2] - A[1]) / (A[1] - A[0]);
        for (int i = 2; i < N; i++) {
            if ((num * diff) != A[i] - A[i - 1]) {
                System.out.println("B");
                return;
            }
            num = num * diff;
        }
        System.out.println(A[N - 1] + num * diff);
    }
}