import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] A = new int[N + 1][2];
        int[] in = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[n][0] = Integer.parseInt(st.nextToken());
            A[n][1] = Integer.parseInt(st.nextToken());
            in[A[n][0]]++;
            in[A[n][1]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 1; n <= N; n++) {
            if (in[n] < 2) q.offer(n);
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            if (--in[A[u][0]] == 1) q.offer(A[u][0]);
            if (--in[A[u][1]] == 1) q.offer(A[u][1]);
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int n = 1; n <= N; n++) {
            if (in[n] != 2) continue;
            sb.append(n).append(" ");
            cnt++;
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}