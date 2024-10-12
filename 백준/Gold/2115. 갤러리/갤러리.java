import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] map, A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        A = new boolean[N][M]; // 위에 벽
        B = new boolean[N][M]; // 왼쪽에 벽
        int answer = 0;
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) == '.';
                if (input.charAt(m) == '.') {
                    if (!map[n - 1][m]) {
                        if (map[n][m - 1] && A[n][m - 1])
                            answer++;
                        else
                            A[n][m] = true;
                    }
                    if (!map[n][m - 1]) {
                        if (map[n - 1][m] && B[n - 1][m])
                            answer++;
                        else
                            B[n][m] = true;
                    }
                } else {
                    if (n == 0 || m == 0)
                        continue;
                    if (map[n - 1][m]) {
                        if (!map[n][m - 1] && A[n][m - 1])
                            answer++;
                        else
                            A[n][m] = true;
                    }
                    if (map[n][m - 1]) {
                        if (!map[n - 1][m] && B[n - 1][m])
                            answer++;
                        else
                            B[n][m] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}