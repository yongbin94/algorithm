import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] A;
    static boolean[] visited;
    static List<Integer> B, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        A = new ArrayList[N + 1];
        B = new ArrayList<>();
        W = new ArrayList<>();
        visited = new boolean[N + 1];
        for(int n = 1; n <= N; n++)
            A[n] = new ArrayList<>();
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                int v = Integer.parseInt(st.nextToken());
                A[n].add(v);
                A[v].add(n);
            }
        }
        solution();
    }

    private static void solution() {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(visited[i])
                continue;
            boolean isBlue = true;
            visited[i] = true;
            B.add(i);
            q.offer(i);
            while (!q.isEmpty()) {
                for (int j = 0, size = q.size(); j < size; j++) {
                    int n = q.poll();
                    for (int k : A[n]) {
                        if (visited[k])
                            continue;
                        visited[k] = true;
                        q.offer(k);
                        if (isBlue)
                            W.add(k);
                        else
                            B.add(k);
                    }
                }
                isBlue = !isBlue;
            }
        }
        Collections.sort(B);
        Collections.sort(W);
        StringBuilder sb =new StringBuilder();
        sb.append(B.size()).append("\n");
        for(int i : B)
            sb.append(i).append(" ");
        sb.append("\n").append(W.size()).append("\n");
        for(int i : W)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}