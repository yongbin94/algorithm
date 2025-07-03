import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, map, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1];
        num = new int[N];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            map[Integer.parseInt(st2.nextToken())] = n;
        }
        A = new int[N];
        for (int n = 0; n < N; n++) {
            int v = Integer.parseInt(st1.nextToken());
            A[n] = map[v];
            num[n] = v;
        }
        solution();
    }

    private static void solution() {
        int[] dp = new int[N];
        int[] pos = new int[N];
        int[] prev = new int[N];
        int len = 0;
        for (int n = 0; n < N; n++) {
            int i = Arrays.binarySearch(dp, 0, len, A[n]);
            if (i < 0) i = -i - 1;
            dp[i] = A[n];
            pos[i] = n;
            prev[n] = (i > 0 ? pos[i - 1] : -1);
            if (i == len) len++;
        }
        TreeSet<Integer> ts = new TreeSet<>();
        int i = pos[len - 1];
        while (i != -1) {
            ts.add(num[i]);
            i = prev[i];
        }
        StringBuilder sb = new StringBuilder();
        while(!ts.isEmpty()) {
            sb.append(ts.pollFirst()).append(" ");
        }
        System.out.println(len); 
        System.out.println(sb);
    }
}