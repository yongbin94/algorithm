import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 0; t < 3; t++) {
            int maxR = 0, minR = 6, maxC = 0, minC = 6;
            int[] row = new int[6];
            int[] col = new int[6];
            for (int r = 0; r < 6; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 6; c++) {
                    if (st.nextToken().equals("1")) {
                        row[r]++;
                        col[c]++;
                        maxR = Math.max(maxR, r);
                        minR = Math.min(minR, r);
                        maxC = Math.max(maxC, c);
                        minC = Math.min(minC, c);
                    }
                }
            }
            if (maxR - minR + maxC - minC == 5) {
                if (maxR - minR == 3) {
                    if (col[maxC] >= 3 || col[minC] >= 3)
                        System.out.println("no");
                    else
                        System.out.println("yes");
                } else if (maxR - minR == 2) {
                    if (row[maxR] >= 3 || row[minR] >= 3)
                        System.out.println("no");
                    else
                        System.out.println("yes");
                } else if (maxR - minR == 1) {
                    if (Arrays.stream(row).map(v -> v % 3).distinct().count() == 1)
                        System.out.println("yes");
                    else
                        System.out.println("no");
                } else if (maxR - minR == 4) {
                    if (row[0] == 0 && row[3] == 2 || row[5] == 0 && row[2] == 2)
                        System.out.println("yes");
                    else
                        System.out.println("no");
                } else
                    System.out.println("no");
            } else
                System.out.println("no");
        }
    }
}