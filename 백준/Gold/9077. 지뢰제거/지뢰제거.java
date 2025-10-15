import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<Pos>[][] A = new ArrayList[1001][1001];
            Arrays.stream(A).forEach(v -> Arrays.setAll(v, w -> new ArrayList<>()));
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                A[r / 10][c / 10].add(new Pos(r, c));
            }
            int res = 0;
            for (int a = 0; a < 1000; a++) {
                for (int b = 0; b < 1000; b++) {
                    if (res >= A[a][b].size() + A[a + 1][b].size() + A[a][b + 1].size() + A[a + 1][b + 1].size())
                        continue;
                    for (int r = 0; r < 10; r++) {
                        for (int c = 0; c < 10; c++) {
                            int cnt = 0;
                            for (int t = 0; t < 4; t++) {
                                for (Pos p : A[a + (t / 2)][b + (t % 2)]) {
                                    int cr = a * 10 + r;
                                    int cc = b * 10 + c;
                                    if (p.r >= cr && p.r <= cr + 10 && p.c >= cc && p.c <= cc + 10) cnt++;
                                }
                            }
                            res = Math.max(res, cnt);
                        }
                    }
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}