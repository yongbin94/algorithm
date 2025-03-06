import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N != 0) {
            char[][] art = new char[0][];
            int C = 0;
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                List<int[]> list = new ArrayList<>();
                for (int m = 0, M = Integer.parseInt(st.nextToken()); m < M; m++) {
                    list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
                }
                if (n == 0) {
                    for (int[] a : list) {
                        C += a[1];
                    }
                    art = new char[N * 2 + 1][C * 3 + 1];
                    Arrays.stream(art).forEach(v -> Arrays.fill(v, '#'));
                }
                for (int[] a : list) {
                    for (int m = 0; m < C; m++) {
                        if (art[n * 2][m * 3] != '#') continue;
                        for (int r = 0; r < a[0]; r++) {
                            for (int c = 0; c < a[1]; c++) {
                                art[r * 2 + n * 2][m * 3 + c * 3] = ' ';
                                art[r * 2 + n * 2][m * 3 + c * 3 + 1] = r == 0 ? '-' : ' ';
                                art[r * 2 + n * 2][m * 3 + c * 3 + 2] = r == 0 ? '-' : ' ';

                                art[r * 2 + n * 2 + 1][m * 3 + c * 3] = c == 0 ? '|' : ' ';
                                art[r * 2 + n * 2 + 1][m * 3 + c * 3 + 1] = r + c == 0 ? (char) (n + '1') : ' ';
                                art[r * 2 + n * 2 + 1][m * 3 + c * 3 + 2] = r + c == 0 ? (char) (m + '1') : ' ';
                            }
                        }
                        break;
                    }

                }
            }
            for (int r = 0; r < N; r++) {
                art[r * 2][C * 3] = ' ';
                art[r * 2 + 1][C * 3] = '|';
            }
            for (int c = 0; c < C; c++) {
                art[N * 2][c * 3] = ' ';
                art[N * 2][c * 3 + 1] = '-';
                art[N * 2][c * 3 + 2] = '-';
            }
            art[N * 2][C * 3] = ' ';
            Arrays.stream(art).forEach(v -> sb.append(String.valueOf(v).replaceAll(" +$", "")).append("\n"));
            sb.append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }
}