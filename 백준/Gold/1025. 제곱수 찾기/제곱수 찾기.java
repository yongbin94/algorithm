import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static Set<Integer> S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) - '0';
            }
        }

        init();
        answer = -1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int n = -N; n <= N; n++) {
                    for (int m = -M; m <= M; m++) {
                        solution(r, c, n, m);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static void init() {
        S = new HashSet<>();
        int i = 0, v = 0;
        while (v < 1000000000) {
            S.add(v);
            v = ++i * i;
        }
    }

    private static void solution(int r, int c, int n, int m) {
        int a = 0, b = 0, t = 1;
        if (n == 0 && m == 0) {
            if (S.contains(map[r][c])) answer = Math.max(answer, map[r][c]);
            return;
        }
        while (r >= 0 && r < N && c >= 0 && c < M) {
            a = a * 10 + map[r][c];
            b += map[r][c] * t;
            t *= 10;
            if (S.contains(a)) answer = Math.max(answer, a);
            if (S.contains(b)) answer = Math.max(answer, b);
            r += n;
            c += m;
        }
    }
}