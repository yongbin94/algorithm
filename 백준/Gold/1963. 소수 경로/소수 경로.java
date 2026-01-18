import java.io.*;
import java.util.*;

public class Main {
	static boolean[] A = new boolean[10000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10000; i++) {
            if (A[i]) continue;
            for (int j = i * i; j < 10000; j += i) A[j] = true;
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int res = bfs(A, B);
            sb.append(res == -1 ? "Impossible" : res).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int start, int end) {
        if (start == end) return 0;

        Queue<int[]> q = new LinkedList<>();
        boolean[] v = new boolean[10000];
        
        q.offer(new int[]{start, 0});
        v[start] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int num = curr[0];
            int dist = curr[1];

            if (num == end) return dist;

            for (int i = 0; i < 4; i++) {
                for (int d = 0; d <= 9; d++) {
                    int next = change(num, i, d);

                    if (next < 1000 || A[next] || v[next]) continue;

                    v[next] = true;
                    q.offer(new int[]{next, dist + 1});
                }
            }
        }
        return -1;
    }

    private static int change(int num, int pos, int digit) {
        int p = 1;
        for (int i = 0; i < 3 - pos; i++) p *= 10;
        return num - (num / p % 10 * p) + (digit * p);
    }
}