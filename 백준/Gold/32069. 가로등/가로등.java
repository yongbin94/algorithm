import java.io.*;
import java.util.*;

public class Main {
    static long L, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Set<Long> visited = new HashSet<>();
        Queue<Long> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            long v = Long.parseLong(st.nextToken());
            visited.add(v);
            q.offer(v);
        }
        int cnt = 0;
        visited.add(-1L);
        visited.add(L + 1L);
        while (!q.isEmpty()) {
            for (int n = 0, size = q.size(); n < size; n++) {
                long i = q.poll();
                sb.append(cnt).append("\n");
                if (--K == 0) {
                    System.out.println(sb);
                    return;
                }
                if (!visited.contains(i - 1)) {
                    visited.add(i - 1L);
                    q.offer(i - 1L);
                }
                if (!visited.contains(i + 1)) {
                    visited.add(i + 1L);
                    q.offer(i + 1L);
                }
            }
            cnt++;
        }
    }
}