import java.io.*;
import java.util.*;

public class Main {
    static final int V = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), cnt = V - 1;
        boolean[] b = new boolean[V + 1];
        b[1] = true;
        for (int i = 2; i <= V; i++) {
            for (int j = i * 2; j <= V; j += i) {
                if (!b[j]) cnt--;
                b[j] = true;
            }
        }
        int[] A = new int[cnt];
        int idx = 0;
        for (int i = 2; i <= V; i++) {
            if (!b[i]) A[idx++] = i;
        }
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Arrays.binarySearch(A, Integer.parseInt(st.nextToken()));
            if (l < 0) l = -l - 1;
            int r = Arrays.binarySearch(A, Integer.parseInt(st.nextToken()));
            if (r < 0) r = -r - 2;
            if ((r - l) % 2 != 0) sb.append(-1).append("\n");
            else sb.append(A[(l + r) / 2]).append("\n");
        }
        System.out.println(sb);
    }
}