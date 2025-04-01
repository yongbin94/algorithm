import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        initMap();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            br.readLine();
            int v = 0;
            for (int i = 0; i < 3; i++) {
                v = v * 1000 + Integer.parseInt(br.readLine().replace("#", "9"));
            }
            sb.append(map.containsKey(v) ? map.get(v) : "impossible").append("\n");
        }
        System.out.println(sb);
    }

    static final int[] POW = {100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1};
    static final int[][] MOVES = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7}, {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}};
    static Queue<Integer> q = new ArrayDeque<>();
    static int cnt = 1;

    private static void initMap() {
        map.put(123456789, 0);
        q.offer(123456789);

        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int n = q.poll();
                int a = find(n);
                for (int b : MOVES[a]) {
                    swap(n, a, b);
                }
            }
            cnt++;
        }
    }

    private static int find(int n) {
        for (int i = 8; i >= 0; i--) {
            if (n % 10 == 9) return i;
            n /= 10;
        }
        return -1;
    }

    static void swap(int n, int a, int b) {
        int digitA = (n / POW[a]) % 10;
        int digitB = (n / POW[b]) % 10;

        int diff = digitA - digitB;

        n -= diff * POW[a];
        n += diff * POW[b];

        if (map.containsKey(n)) return;
        map.put(n, cnt);
        q.offer(n);
    }
}