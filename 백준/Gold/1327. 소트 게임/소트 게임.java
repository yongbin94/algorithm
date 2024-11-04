import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int a = 0, g = 0, t = 0;
        for (int n = 1; n <= N; n++) {
            a = a * 10 + Integer.parseInt(st.nextToken());
            g = g * 10 + n;
        }
        set.add(a);
        queue.add(a);
        while (!queue.isEmpty()) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                int v = queue.poll();
                if (v == g) {
                    System.out.println(t);
                    return;
                }
                for (int j = 0; j < N - K + 1; j++) {
                    int tmp = 0;
                    for (int k = 0; k < N; k++) {
                        if (k == j) {
                            for (int l = N - (K + j); l < N - j; l++)
                                tmp = tmp * 10 + (int) (v % Math.pow(10, l + 1) / Math.pow(10, l));
                            k += K - 1;
                            continue;
                        }
                        tmp = tmp * 10 + (int) (v % Math.pow(10, N - k) / Math.pow(10, N - k - 1));

                    }
                    if (set.contains(tmp))
                        continue;
                    set.add(tmp);
                    queue.offer(tmp);
                }
            }
            t++;
        }
        System.out.println(-1);
    }
}