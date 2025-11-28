import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long total = 0;
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            int L = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[L];
            for (int i = 0; i < L; i++) arr[i] = Integer.parseInt(st.nextToken());

            long curr = 0, sum = Long.MIN_VALUE;
            int start = 0, l = 0, r = 0;

            for (int i = 0; i < L; i++) {
                curr += arr[i];
                if (curr > sum ||
                        (curr == sum && (i - start) < (r - l)) ||
                        (curr == sum && (i - start) == (r - l) && start < l)) {
                    sum = curr;
                    l = start;
                    r = i;
                }
                if (curr <= 0) {
                    curr = 0;
                    start = i + 1;
                }
            }
            total += sum;
            sb.append(l + 1).append(" ").append(r + 1).append("\n");
        }

        System.out.println(total);
        System.out.println(sb);
    }
}
