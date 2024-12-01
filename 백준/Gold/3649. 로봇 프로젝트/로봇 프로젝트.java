import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        while ((input = br.readLine()) != null) {
            int X = Integer.parseInt(input) * 10_000_000;
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            for (int n = 0; n < N; n++)
                A[n] = Integer.parseInt(br.readLine());
            Arrays.sort(A);

            int left = 0;
            int right = N - 1;
            boolean flag = false;
            while (left < right) {
                int s = A[left] + A[right];
                if (s == X) {
                    System.out.printf("yes %d %d\n", A[left], A[right]);
                    flag = true;
                    break;
                }
                if (s > X)
                    right--;
                else
                    left++;
            }
            if (!flag) System.out.println("danger");
        }
    }
}