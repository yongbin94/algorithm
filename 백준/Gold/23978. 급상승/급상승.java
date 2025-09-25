import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 1, r = K;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean check(long x) {
        if (K / x < (x + 1) / 2) return true;
        long X = x * (x + 1) / 2;
        long res = X;
        for (int n = 1; n < N; n++) {
            long a = x - (A[n] - A[n - 1]);
            res -= a > 0 ? a * (a + 1) / 2 : 0;
            if (res >= K) return true;
            res += X;
        }
        return res >= K;
    }
}