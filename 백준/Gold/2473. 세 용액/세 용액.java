import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] A = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
        long sum = Long.MAX_VALUE;
        long[] numbers = new long[3];
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long ij = A[i] + A[j];
                int k = Arrays.binarySearch(A, j + 1, N, ij * -1);
                if (k < -1) {
                    k = (k + 2) * -1;
                    if (k == j || ((k + 1 < N) && (Math.abs(ij + A[k]) > Math.abs(ij + A[k + 1]))))
                        k++;
                }
                if (sum > Math.abs(ij + A[k])) {
                    sum = Math.abs(ij + A[k]);
                    numbers = new long[]{A[i], A[j], A[k]};
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(numbers).forEach(v -> sb.append(v).append(" "));
        System.out.println(sb);
    }
}