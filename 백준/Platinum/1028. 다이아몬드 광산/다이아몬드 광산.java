import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][][] map = new int[2][R + 1][C + 2];
        int answer = 0;
        for (int r = 1; r <= R; r++) {
            String input = br.readLine();
            for (int c = 1; c <= C; c++) {
                if (input.charAt(c - 1) == '0') continue;
                map[0][r][c] = map[0][r - 1][c + 1] + 1;
                map[1][r][c] = map[1][r - 1][c - 1] + 1;
                int max = Math.min(map[0][r][c], map[1][r][c]);
                for (int k = max; k > answer; k--) {
                    int len = k - 1;
                    if (answer > len) continue;
                    if (map[0][r - len][c - len] > len && map[1][r - len][c + len] > len) {
                        answer = len + 1;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}