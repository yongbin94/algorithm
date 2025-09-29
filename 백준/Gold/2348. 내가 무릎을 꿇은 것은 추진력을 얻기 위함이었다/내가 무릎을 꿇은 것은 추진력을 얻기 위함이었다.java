import java.io.*;

public class Main {
    static int[] A;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = input.length();
        A = new int[N];
        int a = 0;
        for (int n = 0; n < N; n++) {
            A[n] = input.charAt(n) - '0';
        }
        answer = Integer.MAX_VALUE;
        int v = 0;
        for (int i = 0; i < 9; i++) {
            if (N == i + 1) break;
            v *= 10;
            v += A[i];
            solution(v, i + 1);
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    private static void solution(int prev, int i) {
        if (A[i] == 0) return;
        int curr = 0;
        for (int j = 0; j < 9; j++) {
            if (N == i + j) return;
            curr *= 10;
            curr += A[i + j];
            if (curr <= prev) continue;
            recur(curr, i + j + 1, curr - prev);
        }
    }

    private static void recur(int prev, int i, int d) {
        if (i == N || A[i] == 0) return;
        if (N - i < 10) {
            int last = 0;
            for (int j = i; j < N; j++) {
                last *= 10;
                last += A[j];
            }
            if (last > prev && last % prev == 0) {
                answer = Math.min(answer, last / prev);
            }
        }
        int goal = prev + d;
        int curr = 0;
        for (int j = 0; j < 9; j++) {
            if (N == i + j) return;
            curr *= 10;
            curr += A[i + j];
            if (curr > goal) return;
            if (curr == goal) {
                recur(curr, i + j + 1, d);
                return;
            }
        }
    }
}