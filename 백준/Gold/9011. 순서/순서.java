import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solution(N, new StringTokenizer(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }

    private static String solution(int N, StringTokenizer st) {
        int[] A = new int[N];
        for (int n = 0; n < N; n++) {
            int v = Integer.parseInt(st.nextToken());
            if (v > n) return "IMPOSSIBLE";
            for (int m = 0; m < n; m++) {
                if (A[m] > v) A[m]++;
            }
            A[n] = v + 1;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(A).forEach(v -> sb.append(v).append(" "));
        return sb.toString();
    }
}