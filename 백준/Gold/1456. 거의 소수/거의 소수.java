import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int max = 10000000;
        boolean[] P = new boolean[max + 1];
        P[0] = P[1] = true;

        for (int i = 2; i * i <= max; i++) {
            if (P[i]) continue;
            for (long j = (long)i * i; j <= max; j += i) {
                P[(int)j] = true;
            }
        }

        int res = 0;
        for (int i = 2; i <= max; i++) {
            if (P[i]) continue;
            
            long tmp = i;
            while ((double)i <= (double)B / tmp) {
                tmp *= i;
                if (tmp >= A) res++;
            }
        }

        System.out.println(res);
    }
}