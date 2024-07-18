import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int[] row = new int[N + 1];
        int[] col = new int[N + 1];
        int row_max = 0;
        int row_cnt = 0;
        int cal_max = 0;
        int cal_cnt = 0;

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            if (t == 1) {
                row[a]++;
                if (row_max < row[a]) {
                    row_max = row[a];
                    row_cnt = 1;
                } else if (row_max == row[a])
                    row_cnt++;
            }
            if (t == 2) {
                col[a]++;
                if (cal_max < col[a]) {
                    cal_max = col[a];
                    cal_cnt = 1;
                } else if (cal_max == col[a])
                    cal_cnt++;
            }
            if(row_cnt == 0)
                sb.append(cal_cnt * N);
            else if(cal_cnt == 0)
                sb.append(row_cnt * N);
            else
                sb.append(row_cnt * cal_cnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}