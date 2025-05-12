import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String A = br.readLine();
        String B = br.readLine();
        boolean s1, s2, c1, c2;
        s1 = c1 = A.charAt(0) == '.';
        s2 = c2 = B.charAt(0) == '.';
        if (!s1 && !s2) {
            System.out.println(-1);
            return;
        }
        long res = 0;
        for (int n = 1; n < N; n++) {
            boolean a = A.charAt(n) == '.';
            boolean b = B.charAt(n) == '.';
            if (s1 && s2) {
                s1 = a;
                s2 = b;
                if (s1 && s2) {
                    res++;
                    continue;
                }
                if (!s1) c1 = false;
                if (!s2) c2 = false;
            }
            if (!a && !b) {
                System.out.println(-1);
                return;
            }
            if (c1 && !a) {
                if (B.charAt(n - 1) == '#') {
                    System.out.println(-1);
                    return;
                }
                res++;
                c1 = false;
                c2 = true;
            } else if (c2 && !b) {
                if (A.charAt(n - 1) == '#') {
                    System.out.println(-1);
                    return;
                }
                res++;
                c1 = true;
                c2 = false;
            }
            res++;
        }
        if (K > 1) {
            if ((A.charAt(N - 1) == '#' && B.charAt(0) == '#') || (B.charAt(N - 1) == '#' && A.charAt(0) == '#')) {
                System.out.println(-1);
                return;
            }
            boolean tmp = (s1 && c2) ^ (s2 && c1);
            res = (res + (tmp ? 2 : 1)) * K - (tmp ? 2 : 1);
        }
        System.out.println(res);
    }
}