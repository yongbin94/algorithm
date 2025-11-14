import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static IceCream[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new IceCream[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = new IceCream(n + 1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(A, (o1, o2) -> o2.a - o1.a);
        StringBuilder sb = new StringBuilder();
        int next = 0, l = 0, r = -1;
        boolean reversed = false;
        while (M-- > 0) {
            if (l > r) {
                l = next;
                r = next;
                while (r < N && A[l].a == A[r].a) {
                    r++;
                }
                next = r--;
            }
            if (!reversed) {
                if (A[l].a % 7 == 0) reversed = true;
                sb.append(A[l++].n).append("\n");
            } else {
                if (A[r].a % 7 == 0) reversed = false;
                sb.append(A[r--].n).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static class IceCream {
        int n, a;

        public IceCream(int n, int a) {
            this.n = n;
            this.a = a;
        }
    }
}