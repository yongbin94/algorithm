import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()), t = 0, W = Integer.parseInt(st.nextToken());
        int[][] A = new int[W + 1][W + 3];
        Queue<Jadu> q = new ArrayDeque<>();
        q.offer(new Jadu(1, 0, 0));
        int result = 0;
        while (t++ < T) {
            int w = Integer.parseInt(br.readLine());
            for (int i = 0; i <= W; i++) {
                if (w > t + i)
                    continue;
                A[i][w]++;
                if (i == 0)
                    continue;
                A[i][w] = Math.max(A[i][w], A[i - 1][w - 1] + 1);
                A[i][w] = Math.max(A[i][w], A[i - 1][w + 1] + 1);
            }
        }
        System.out.println(Arrays.stream(A[W]).max().getAsInt());
    }

    private static class Jadu {
        int w, cnt, score;

        public Jadu(int w, int cnt, int score) {
            this.w = w;
            this.cnt = cnt;
            this.score = score;
        }
    }
}