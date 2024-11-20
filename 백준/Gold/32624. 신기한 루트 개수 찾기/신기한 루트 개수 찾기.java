import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B, answer;
    static List<Integer>[] E;
    static boolean[] visited;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;
        E = new ArrayList[N];
        visited = new boolean[N];
        Arrays.setAll(E, e -> new ArrayList<>());
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            E[a].add(b);
            E[b].add(a);
        }
        visited[A] = true;
        for (int i : E[A]) {
            answer = 0;
            visited[i] = true;
            dfs(i);
            if(flag)
                break;
        }
        System.out.println(answer);
    }

    private static void dfs(int a) {
        if (a == B) {
            flag = true;
            return;
        }
        answer++;
        for (int b : E[a]) {
            if (visited[b])
                continue;
            visited[b] = true;
            dfs(b);
        }
    }
}
