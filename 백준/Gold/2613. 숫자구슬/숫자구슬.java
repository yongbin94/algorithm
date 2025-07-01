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
        A[0] = Integer.parseInt(st.nextToken());
        for (int n = 1; n < N; n++) {
            A[n] = A[n - 1] + Integer.parseInt(st.nextToken());
        }
        print(solution());
    }

    private static void print(int max) {
        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");
        int v = max;
        int prev = -1;
        for (int m = 0; m < M; m++) {
            int i = Arrays.binarySearch(A, v);
            if (i < 0) i = -i - 2;
            if (N - i < M - m) {
                int t = (i - prev) - (M - m - N + i);
                sb.append(t).append(" ");
                for (m++; m < M; m++) {
                    sb.append("1 ");
                }
                break;
            }
            v = A[i] + max;
            sb.append(i - prev).append(" ");
            prev = i;
        }
        System.out.println(sb);
    }

    private static int solution() {
        int l = 0, r = N * 100;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (calc(mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private static boolean calc(int max) {
        int v = max;
        for (int m = 0; m < M; m++) {
            int i = Arrays.binarySearch(A, v);
            if (i < 0) i = -i - 2;
            if (i < 0) return false;
            if (i >= N - 1) return true;
            v = A[i] + max;
        }
        return false;
    }
}