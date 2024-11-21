import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[][] map;
    static int[] top = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][6];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++)
                map[n][i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= 6; i++)
            recur(0, i, 0);
        System.out.println(answer);
    }

    private static void recur(int cnt, int prev, int sum) {
        if (cnt == N) {
            answer = Math.max(answer, sum);
            return;
        }
        int max = 0;
        int curr = 0;
        for (int i = 0; i < 6; i++)
            if (map[cnt][i] == prev)
                curr = i;
        for (int i = 0; i < 6; i++) {
            if (i == curr || top[i] == curr)
                continue;
            max = Math.max(max, map[cnt][i]);
        }
        recur(cnt + 1, map[cnt][top[curr]], sum + max);
    }
}