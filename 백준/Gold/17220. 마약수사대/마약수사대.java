import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static List<Integer>[] list;
    static boolean[] visited;
    static int[] in;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        list = new ArrayList[N];
        in = new int[N];
        for (int n = 0; n < N; n++)
            list[n] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            list[a].add(b);
            in[b]++;
        }
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        while (st.hasMoreTokens())
            visited[st.nextToken().charAt(0) - 'A'] = true;
        for(int n = 0; n < N;n++)
            if(in[n] == 0 && !visited[n])
                solution(n);
        System.out.println(answer);
    }

    private static void solution(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        visited[n] = true;
        while (!q.isEmpty()) {
            int s = q.poll();
            for (int e : list[s]) {
                if (visited[e])
                    continue;
                visited[e] = true;
                answer++;
                q.offer(e);
            }
        }
    }
}