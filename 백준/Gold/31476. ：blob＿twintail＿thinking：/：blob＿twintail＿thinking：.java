import java.io.*;
import java.util.*;

public class Main {
    static int D, N, U, T;
    static boolean[] destroyed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        destroyed = new boolean[1 << D];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            destroyed[Math.max(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))] = true;
        }
        System.out.printf(":blob_twintail_%s:\n", solution());
    }

    private static String solution() {
        int twin = 0, pony = 0;
        boolean flag = false;
        Queue<Blob> q = new ArrayDeque<>();
        q.offer(new Blob(1, 0, 0));
        for (int d = 0; d < D; d++) {
            for (int i = 1, size = q.size(); i <= size; i++) {
                Blob u = q.poll();
                pony += (!flag && i == size) ? 1 : 2;
                twin = Math.max(twin, u.total);
                if (d == D - 1) continue;
                int a = u.i * 2;
                int b = u.i * 2 + 1;
                if (!destroyed[a]) {
                    int nv = u.v + (destroyed[b] ? 0 : 1);
                    q.offer(new Blob(a, nv, u.total + (U + T * nv)));
                }
                if (!destroyed[b]) {
                    int nv = u.v + (destroyed[a] ? 0 : 1);
                    q.offer(new Blob(b, nv, u.total + (U + T * nv)));
                }
                if (!flag && size == i && destroyed[a] && destroyed[b]) {
                    flag = true;
                }
            }
        }
        pony = (pony - 1) * U;
        return twin < pony ? "aww" : twin > pony ? "sad" : "thinking";
    }

    private static class Blob {
        int i, v, total;

        public Blob(int i, int v, int total) {
            this.i = i;
            this.v = v;
            this.total = total;
        }
    }
}