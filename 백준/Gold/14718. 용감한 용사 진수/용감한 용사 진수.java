import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;
    static Target[] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = new Target[N];
        answer = Integer.MAX_VALUE;
        for (int n = 0; n < N; n++) {
            T[n] = new Target(new StringTokenizer(br.readLine()));
        }

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                for (int k = j; k < N; k++) {
                    Target nt = new Target(T[i], T[j], T[k]);
                    nt.update();
                }
            }
        }

        System.out.println(answer);
    }

    private static class Target {
        int s, d, i;

        public Target(StringTokenizer st) {
            this.s = Integer.parseInt(st.nextToken());
            this.d = Integer.parseInt(st.nextToken());
            this.i = Integer.parseInt(st.nextToken());
        }

        public Target(Target a, Target b, Target c) {
            this.s = Math.max(a.s, Math.max(b.s, c.s));
            this.d = Math.max(a.d, Math.max(b.d, c.d));
            this.i = Math.max(a.i, Math.max(b.i, c.i));
        }

        public void update() {
            if (this.s + this.d + this.i > answer) {
                return;
            }
            
            int res = 0;
            for (Target t : T) {
                if (s >= t.s && d >= t.d && i >= t.i) {
                    res++;
                }
            }
            
            if (res >= K) {
                answer = Math.min(answer, s + d + i);
            }
        }
    }
}