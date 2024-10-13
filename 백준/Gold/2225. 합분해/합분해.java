import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[][] map = new long[N][M];
        for (int n = 0; n < N; n++)
            map[n][0] = 1;
        for (int m = 0; m < M; m++)
            map[0][m] = m + 1;
        for (int n = 1; n < N; n++)
            for (int m = 1; m < M; m++)
                map[n][m] = (map[n][m - 1] + map[n - 1][m]) % 1_000_000_000L;
        System.out.println(map[N - 1][M - 1]);
    }
}