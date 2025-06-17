import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Long.parseLong(st.nextToken());
        }
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 1, r = N;
        while (l < r) {
            int mid = (l + r) / 2;
            if (calc(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean calc(int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int n = 0; n < k; n++) {
            pq.offer(A[n]);
        }
        for (int n = k; n < N; n++) {
            long v = A[n] + pq.poll();
            if (v > X) return false;
            pq.offer(v);
        }
        return true;
    }
}