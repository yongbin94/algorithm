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
        int L = 0, R = 0, answer = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            R = Math.max(R, (A[n] = Integer.parseInt(st.nextToken())));
        while (L < R) {
            int mid = (L + R) / 2;
            if (solution(mid) > M) {
                L = mid + 1;
            } else {
                answer = Math.min(answer, mid);
                R = mid;
            }
        }
        System.out.println(answer);
    }

    private static int solution(int v) {
        int res = 1, min = Integer.MAX_VALUE, max = 0;
        for (int n = 0; n < N; n++) {
            min = Math.min(min, A[n]);
            max = Math.max(max, A[n]);
            if (max - min > v) {
                min = A[n];
                max = A[n];
                res++;
            }
        }
        return res;
    }
}