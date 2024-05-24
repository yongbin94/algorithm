import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[101];
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a] = b;
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a] = b;
        }
        int time = 0;
        boolean[] visited = new boolean[101];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (true) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int s = q.poll();
                if(s == 100) {
                    System.out.println(time);
                    return;
                }
                for (int j = 1; j <= 6; j++) {
                    int v = s + j;
                    if (v > 100)
                        break;
                    if (visited[v])
                        continue;
                    if (A[v] != 0) {
                        visited[v] = true;
                        visited[A[v]]= true;
                        q.offer(A[v]);
                        continue;
                    }
                    visited[v] = true;
                    q.offer(v);
                }
            }
            time++;
        }
    }
}
