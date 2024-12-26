import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K, T;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        if (Arrays.stream(A).sum() % K != 0) {
            System.out.println("NO");
            return;
        }
        Arrays.sort(A);
        int left = 0;
        int right = N - 1;
        while (right > left) {
            long tmp = K - A[right];
            if (tmp > A[left]) {
                A[right] += A[left];
                T -= A[left];
                left++;
            } else if (tmp < A[left]) {
                A[left] -= tmp;
                T -= tmp;
                right--;
            } else {
                T -= tmp;
                left++;
                right--;
            }
            if (T < 0) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}