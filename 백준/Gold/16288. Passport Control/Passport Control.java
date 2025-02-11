import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[K];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            int target = -1;
            int min = 100_000;
            for (int k = 0; k < K; k++) {
                if (v > A[k] && v - A[k] < min) {
                    target = k;
                    min = v - A[k];
                }
            }
            if(target == -1) {
                System.out.println("NO");
                return;
            }
            A[target] = v;
        }
        System.out.println("YES");
    }
}