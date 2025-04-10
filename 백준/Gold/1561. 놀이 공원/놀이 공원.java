import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static int M, answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = -1;
        A = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            A[m] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println((int) N);
            return;
        }

        long l = 0, r = N * 30L;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (binarySearch(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(long time) {
        long cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += time / A[i];
        }

        if (cnt < N) return false;

        cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += (time - 1) / A[i];
        }

        for (int i = 0; i < M; i++) {
            if (time % A[i] == 0) {
                cnt++;
                if (cnt == N) {
                    answer = i + 1;
                    break;
                }
            }
        }
        return true;
    }
}