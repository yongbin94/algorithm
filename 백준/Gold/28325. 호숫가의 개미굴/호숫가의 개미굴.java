import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] c = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
            
        long res = 0;
        int p = -1;
        for (int i = 0; i < n; i++) {
            c[i] = Long.parseLong(st.nextToken());
            if (c[i] > 0) {
                res += c[i]; 
                if (p == -1) p = i;
            }
        }

        if (p == -1) {
            System.out.println(n / 2);
            return;
        }

        int z = 0;
        for (int i = 1; i <= n; i++) {
            int idx = (p + i) % n;
            if (c[idx] == 0) {
                z++;
                continue;
            }
            if (z > 0) {
                res += (z + 1) / 2;
                z = 0;
            }
        }
        System.out.println(res);
    }
}