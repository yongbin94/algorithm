import java.io.*;
import java.util.*;

public class Main {
    static int[] A;
    static int[][] B, C;
    static boolean[] used;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = new int[12];
        map = new char[5][];
        used = new boolean[12];
        B = new int[][]{{1, 2, 3, 4}, {7, 8, 9, 10}, {0, 2, 5, 7}, {0, 3, 6, 10}, {1, 5, 8, 11}, {4, 6, 9, 11}};
        C = new int[][]{{0, 4}, {1, 1}, {1, 3}, {1, 5}, {1, 7}, {2, 2}, {2, 6}, {3, 1}, {3, 3}, {3, 5}, {3, 7}, {4, 4}};
        for (int r = 0; r < 5; r++)
            map[r] = br.readLine().toCharArray();
        for (int i = 0; i < 12; i++)
            A[i] = map[C[i][0]][C[i][1]] - 'A';
        for (int i = 0; i < 12; i++)
            if (A[i] < 12)
                used[A[i]] = true;
        recur(0);
        for (int i = 0; i < 12; i++)
            map[C[i][0]][C[i][1]] = (char) (A[i] + 'A');
        StringBuilder sb = new StringBuilder();
        for(int n = 0; n < 5; n++) {
            for(char c : map[n])
                sb.append(c);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean recur(int cnt) {
        if (cnt == 12) {
            for (int[] arr : B) {
                if (Arrays.stream(arr).map(v -> A[v]).sum() != 22)
                    return false;
            }
            return true;
        }
        if (A[cnt] != 55)
            return recur(cnt + 1);
        for (int i = 0; i < 12; i++) {
            if (used[i])
                continue;
            used[i] = true;
            A[cnt] = i;
            if (recur(cnt + 1))
                return true;
            used[i] = false;
        }
        A[cnt] = 55;
        return false;
    }
}