import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] map;
    static int N, M, F, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) == '#';
            }
        }

        // 가로 탐색
        for (int r = 0; r < N - 1; r++) {
            boolean isFirst = true;
            int l = 0;
            for (int c = 0; c < M; c++) {
                if (map[r][c] && map[r + 1][c]) {
                    if (isFirst) {
                        isFirst = false;
                        l = Math.min(l, F);
                    }
                    answer += l;
                    l = 0;
                } else if (map[r][c] || map[r + 1][c]) {
                    l++;
                }
            }
            answer += Math.min(l, F);
        }
        // 세로 탐색
        for (int c = 0; c < M - 1; c++) {
            boolean isFirst = true;
            int l = 0;
            for (int r = 0; r < N; r++) {
                if (map[r][c] && map[r][c + 1]) {
                    if (isFirst) {
                        isFirst = false;
                        l = Math.min(l, F);
                    }
                    answer += l;
                    l = 0;
                } else if (map[r][c] || map[r][c + 1]) {
                    l++;
                }
            }
            answer += Math.min(l, F);
        }

        System.out.println(answer);
    }
}