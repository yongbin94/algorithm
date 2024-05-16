import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[M * 2];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        int time = 0;
        int answer = 0;
        if(N > M) {
            System.out.println(N - M);
            System.out.println(1);
            return;
        }
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int v = q.poll();
                visited[v] = true;
                if(v == M)
                    answer++;
                if (v - 1 >= 0 && !visited[v - 1])
                    q.offer(v - 1);
                if (v + 1 < M * 2 && !visited[v + 1])
                    q.offer(v + 1);
                if (v * 2 < M * 2 && !visited[v * 2])
                    q.offer(v * 2);
            }
            if (answer != 0) {
                System.out.println(time);
                System.out.println(answer);
                return;
            }
            time++;
        }
    }
}