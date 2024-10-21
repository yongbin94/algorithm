import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[7];
        while (st.hasMoreTokens())
            A[Integer.parseInt(st.nextToken()) % 7]++;
        int bit = 1;
        for (int i = 1; i < 7; i++) {
            if (A[i] == 0) 
                continue;
            int tmp = 0;
            for (int k = 0; k < 7; k++) {
                if (((bit >> k) & 1) == 0)
                    continue;
                for (int j = 1; j <= Math.min(A[i], 9); j++) {
                    int n = (k + i * j) % 7;
                    if (n == 4) {
                        System.out.println("YES");
                        return;
                    }
                    tmp |= 1 << n;
                }
            }
            bit |= tmp;
        }
        System.out.println("NO");
    }
}