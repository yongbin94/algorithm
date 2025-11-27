import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int I = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<Integer> R = new HashSet<>();
        Set<Integer> C = new HashSet<>();
        Fish[] F = new Fish[M];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            F[m] = new Fish(r, c);
            R.add(r);
            C.add(c);
        }
        int answer = 0;
        for (int r : R) {
            for (int c : C) {
                for (int i = 1; i < I / 2; i++) {
                    int cnt = 0;
                    int er = r + i;
                    int ec = c + I / 2 - i;
                    for (Fish f : F) {
                        if (f.r >= r && f.r <= er && f.c >= c && f.c <= ec) cnt++;
                    }
                    answer = Math.max(answer, cnt);
                }
            }
        }
        System.out.println(answer);
    }

    private static class Fish {
        int r, c;

        public Fish(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}