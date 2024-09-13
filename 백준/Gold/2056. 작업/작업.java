import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] in;
    static int[] T;
    static int[] max;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        in = new int[N];
        max = new int[N];
        list = new ArrayList[N];
        for (int n = 0; n < N; n++)
            list[n] = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            T[n] = t;
            in[n] += m;
            while (st.hasMoreTokens()) {
                int e = Integer.parseInt(st.nextToken()) - 1;
                list[e].add(n);
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 0; n < N; n++)
            if (in[n] == 0) {
                q.offer(n);
                max[n] = T[n];
            }
        int count = 0, answer = 0;
        while (!q.isEmpty()) {
            int s = q.poll();
            for (int e : list[s]) {
                if (--in[e] == 0)
                    q.offer(e);
                max[e] = Math.max(max[e], max[s] + T[e]);
            }
        }
        System.out.println(Arrays.stream(max).max().getAsInt());
    }
}