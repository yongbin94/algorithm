import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        for(int n = 0; n < N; n++)
            A[n] = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if (A[j] < A[i] ) {
                    B[i] = Math.max(B[i], B[j] + 1);
                }
            }
        }
        System.out.println(N - Arrays.stream(B).max().getAsInt() - 1);
    }
}