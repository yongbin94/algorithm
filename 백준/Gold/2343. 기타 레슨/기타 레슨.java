import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = Arrays.stream(A).max().getAsInt(), r = N * 10_000L;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean check(long v) {
        int res = 1;
        long sum = 0;
        for (int n = 0; n < N; n++) {
            if (sum + A[n] > v) {
                sum = 0;
                if (++res > M) return false;
            }
            sum += A[n];
        }
        return true;
    }
}