import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[] A, S;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        S = new int[N];
        selected = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        recur(0);
        System.out.println(answer);
    }

    private static void recur(int i) {
        if (i == N) {
            soluion();
            return;
        }
        for (int n = 0; n < N; n++) {
            if (selected[n]) continue;
            selected[n] = true;
            S[i] = n;
            recur(i + 1);
            selected[n] = false;
        }
    }

    private static void soluion() {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        int v = 0;
        for (int n = 0; n < N; n++) {
            v += A[S[n]];
            if (set.contains(v - 50)) res++;
            set.add(v);
        }
        answer = Math.max(answer, res);
    }
}