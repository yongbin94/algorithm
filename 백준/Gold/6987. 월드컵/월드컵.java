import java.io.*;
import java.util.*;

public class Main {
    static int[][] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = new int[6][3];
            B = new int[6][3];
            int total = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                    total += A[i][j];
                }
            }
            sb.append(total == 30 ? recur(0, 1) ? 1 : 0 : 0).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean recur(int i, int j) {
        if (i == 6) return true;
        if (j == 6) return recur(i + 1, i + 2);
        for (int k = 0; k < 3; k++) {
            if (A[i][k] <= B[i][k] || A[j][2 - k] <= B[j][2 - k]) continue;
            B[i][k]++;
            B[j][2 - k]++;
            if (recur(i, j + 1)) return true;
            B[i][k]--;
            B[j][2 - k]--;
        }
        return false;
    }
}