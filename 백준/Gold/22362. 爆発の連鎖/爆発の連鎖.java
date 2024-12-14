import java.io.*;
import java.util.*;

public class Main {
    static int W, H, N, D, B;
    static Set<Integer> bomb;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (W + H + N + D + B == 0)
                break;
            bomb = new HashSet<>();
            set = new HashSet<>();
            Queue<Integer> q = new ArrayDeque<>();
            for (int n = 1; n <= N; n++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                int v = r * W + c;
                bomb.add(v);
                if (n == B) {
                    set.add(v);
                    q.offer(v);
                }
            }
            while (!q.isEmpty()) {
                int hash = q.poll();
                int r = hash / W;
                int c = hash % W;
                for (int d = 0; d < 4; d++) {
                    for (int i = 1; i <= D; i++) {
                        int nr = r + dr[d] * i;
                        int nc = c + dc[d] * i;
                        if (!isIn(nr, nc))
                            break;
                        int v = nr * W + nc;
                        if (!bomb.contains(v) || set.contains(v))
                            continue;
                        set.add(v);
                        q.offer(v);
                    }
                }
            }
            sb.append(set.size()).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }
}