import java.io.*;
import java.util.*;

public class Main {
    static final long X = 1_000_001;
    static int N, T;
    static Set<Long> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        set = new HashSet<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            set.add(y * X + x);
        }
        solution();
    }

    private static void solution() {
        Queue<Long> q = new ArrayDeque<>();
        q.offer(0L);
        int time = 0;
        while (!q.isEmpty()) {
            for (int n = 0, size = q.size(); n < size; n++) {
                long tmp = q.poll();
                long x = tmp % X;
                long y = tmp / X;
                if(y == T) {
                    System.out.println(time);
                    return;
                }
                for (int a = -2; a <= 2; a++) {
                    for (int b = -2; b <= 2; b++) {
                        long nx = x + a;
                        long ny = y + b;
                        if (!isIn(nx, ny))
                            continue;
                        long nv = ny * X + nx;
                        if(set.contains(nv)) {
                            set.remove(nv);
                            q.offer(nv);
                        }
                    }
                }
            }
            time++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(long x, long y) {
        return x >= 0 && x < X && y >= 0 && y <= T;
    }
}
