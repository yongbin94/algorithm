import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static int[][] map;
    static int max, maxR, maxC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());
        map = new int[100][100];
        for (int r = 0; r < 3; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    private static int solution() {
        maxR = 3;
        maxC = 3;
        for (int cnt = 0; cnt <= 100; cnt++) {
            if (map[R][C] == K) return cnt;
            if (maxR >= maxC) {
                for (int r = 0; r < maxR; r++) {
                    int[] A = new int[101];
                    for (int c = 0; c < maxC; c++) {
                        A[map[r][c]]++;
                    }
                    map[r] = transforn(A);
                    maxC = Math.max(maxC, max);
                }
            } else {
                for (int c = 0; c < maxC; c++) {
                    int[] A = new int[101];
                    for (int r = 0; r < maxR; r++) {
                        A[map[r][c]]++;
                    }
                    int[] tmp = transforn(A);
                    for (int r = 0; r < 100; r++) {
                        map[r][c] = tmp[r];
                    }
                    maxR = Math.max(maxR, max);
                }
            }
        }
        return -1;
    }

    private static int[] transforn(int[] A) {
        int[] res = new int[101];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 1; i <= 100; i++) {
            if (A[i] > 0) pq.offer(new int[]{i, A[i]});
        }
        max = Math.min(100, pq.size() * 2);
        for (int n = 0; n < 100 && !pq.isEmpty(); n += 2) {
            res[n] = pq.peek()[0];
            res[n + 1] = pq.poll()[1];
        }
        return res;
    }
}