import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A;
    static List<Integer>[] J;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        J = new ArrayList[N + 1];
        Arrays.setAll(J, v -> new ArrayList<>());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for(int n = 2; n <= N; n++)
            J[Integer.parseInt(st.nextToken())].add(n);
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
        Queue<Integer> q =new ArrayDeque<>();
        q.offer(1);
        while(!q.isEmpty()) {
            int v = q.poll();
            for(int i : J[v]) {
                A[i] += A[v];
                q.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int n = 1; n <= N; n++)
            sb.append(A[n]).append(" ");
        System.out.println(sb);
    }
}