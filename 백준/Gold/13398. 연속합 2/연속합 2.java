import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static int[] A, dp1, dp2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp1 = new int[N];
        dp2 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());

        dp1[0] = A[0];
        dp2[0] = A[0];
        max = A[0];

        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1] + A[i], A[i]);
            dp2[i] = Math.max(dp2[i - 1] + A[i], dp1[i - 1]);
            max = Math.max(max, Math.max(dp1[i], dp2[i]));
        }

        System.out.println(max);
    }
}