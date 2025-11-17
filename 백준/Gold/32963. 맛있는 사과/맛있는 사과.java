import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static Apple[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        A = new Apple[N];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st1.nextToken());
            A[n] = new Apple(t, s, 0);
            T = Math.max(T, t);
        }

        Arrays.sort(A, (o1, o2) -> o1.t - o2.t);

        int max = 0, cnt = 0;
        for (int n = N - 1; n >= 0; n--) {
            if (A[n].s > max) {
                max = A[n].s;
                cnt = 1;
            } else if (A[n].s == max) {
                cnt++;
            }
            A[n].w = cnt;
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            sb.append(binarySearch(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int i) {
        if (T < i) return 0;

        int l = 0, r = N - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid].t >= i) r = mid;
            else l = mid + 1;
        }

        return A[l].w;
    }

    private static class Apple {
        int t, s, w;

        public Apple(int t, int s, int w) {
            this.t = t;
            this.s = s;
            this.w = w;
        }
    }
}