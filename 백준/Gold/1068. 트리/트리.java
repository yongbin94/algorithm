import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] list = new ArrayList[N];
        for (int n = 0; n < N; n++)
            list[n] = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (v == -1) {
                q.offer(i);
                continue;
            }
            list[v].add(i);
        }
        int answer = 0;
        int K = Integer.parseInt(br.readLine());
        list[K] = new ArrayList<>();
        while (!q.isEmpty()) {
            int v = q.poll();
            if(v == K)
                continue;
            if (list[v].isEmpty())
                answer++;
            if((list[v].size() == 1 && list[v].get(0) == K)) {
                answer++;
                continue;
            }
            for (int i : list[v])
                q.offer(i);
        }
        System.out.println(answer);
    }
}
