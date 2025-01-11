import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000;
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int n = 0; n < N; n++) {
                Arrays.fill(map[n], INF);
                map[n][n] = 0;
            }
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == j)
                            continue;
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
            int K = Integer.parseInt(br.readLine());
            int[] A = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < K; k++)
                A[k] = Integer.parseInt(st.nextToken()) - 1;
            int min = INF;
            int res = -1;
            for (int n = 0; n < N; n++) {
                int tmp = 0;
                for (int i : A)
                    tmp += map[n][i];
                if(min > tmp) {
                    min = tmp;
                    res = n;
                }
            }
            sb.append(res + 1).append("\n");
        }
        System.out.println(sb);
    }
}