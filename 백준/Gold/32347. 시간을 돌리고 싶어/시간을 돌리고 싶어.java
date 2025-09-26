import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static List<Integer> A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new ArrayList<>();
        A.add(1);
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            if (st.nextToken().charAt(0) == '1') A.add(n);
        }
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 1, r = N;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean check(int t) {
        int n = N, k = 0;
        while (n > 1) {
            int v = Collections.binarySearch(A, n - t);
            if (v < 0) v = -v - 1;
            if (n == A.get(v) || k++ == K) return false;
            n = A.get(v);
        }
        return true;
    }
}