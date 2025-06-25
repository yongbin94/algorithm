import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (N == 1) {
            System.out.println(Arrays.stream(A).sum() - Arrays.stream(A).max().getAsInt());
            return;
        }
        long one = Arrays.stream(A).min().getAsInt();
        int[][] arr2 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {1, 3}, {1, 5}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
        long two = Arrays.stream(arr2).mapToLong(a -> A[a[0]] + A[a[1]]).min().getAsLong();
        int[][] arr3 = {{0, 1, 2}, {0, 1, 3}, {0, 2, 4}, {0, 3, 4}, {1, 2, 5}, {1, 3, 5}, {2, 4, 5}, {3, 4, 5}};
        long three = Arrays.stream(arr3).mapToLong(a -> A[a[0]] + A[a[1]] + A[a[2]]).min().getAsLong();
        long res = 0;
        res += one * ((N - 2) * (N - 2) * 5 + (N - 2) * 4);
        res += two * ((N - 2) * 8 + 4);
        res += three * 4;
        System.out.println(res);
    }
}