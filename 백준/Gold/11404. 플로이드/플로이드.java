import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        Arrays.stream(map).forEach(row -> Arrays.fill(row, INF));

        for (int n = 0; n < N; n++)
            map[n][n] = 0;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            map[s][e] = Math.min(map[s][e],w);
        }

        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                sb.append(map[i][j] == INF ? 0 :map[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}