import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] in, parent;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        in = new int[N];
        parent = new int[N];
        A = new long[N];
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            long b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;
            if (a == 'W')
                b *= -1;
            parent[n] = c;
            A[n] = b;
            in[c]++;
        }
        in[0] = -1;
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 0; n < N; n++)
            if (in[n] == 0) {
                A[n] = Math.max(A[n], 0);
                q.offer(n);
            }
        while (!q.isEmpty()) {
            int v = q.poll();
            A[parent[v]] += Math.max(A[v], 0);
            if (--in[parent[v]] == 0)
                q.offer(parent[v]);
        }
        System.out.println(A[0]);
    }
}