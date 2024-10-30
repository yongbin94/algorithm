import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N * 2];
        String A = br.readLine();
        A += br.readLine();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        int time = 0;
        int[] W = {-1, 1, K};
        while (!q.isEmpty()) {
            time++;
            for(int s = 0, size = q.size(); s < size; s++) {
            int i = q.poll();
            for (int j = 0; j < 3; j++) {
                if ((i % N) + W[j] >= N) {
                    System.out.println(1);
                    return;
                }
                int ni = i + W[j] + (j == 2 ? i < N ? N : N * -1 : 0);
                if (ni < 0 || visited[ni] || (ni % N) < time || A.charAt(ni) == '0')
                    continue;
                visited[ni] = true;
                q.offer(ni);
            }
            }
        }
        System.out.println(0);
    }
}