import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static long answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        long l = 0, r = Arrays.stream(A).sum();
        while (l <= r) {
            long mid = (r + l) / 2;
            if (check(mid)) {
                l = mid + 1;
                answer = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean check(long v) {
        long tmp = 0;
        for (int n = 0, k = 0; n < N; n++) {
            tmp += A[n];
            if (tmp >= v) {
                tmp = 0;
                if (++k == K) {
                    return true;
                }
            }
        }
        return false;
    }
}