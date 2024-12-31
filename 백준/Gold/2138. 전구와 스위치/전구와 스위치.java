import java.io.*;

public class Main {
    static int N, answer;
    static boolean[] A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new boolean[N];
        B = new boolean[N];
        answer = Integer.MAX_VALUE;
        String input = br.readLine();
        for (int n = 0; n < N; n++) {
            A[n] = input.charAt(n) == '1';
            B[n] = input.charAt(n) == '1';
        }
        B[0] = !B[0];
        B[1] = !B[1];

        C = new boolean[N];
        input = br.readLine();
        for (int n = 0; n < N; n++)
            C[n] = input.charAt(n) == '1';

        solution(A, C, 0);
        solution(B, C, 1);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void solution(boolean[] a, boolean[] b, int cnt) {
        for (int n = 1; n < N; n++) {
            if (a[n - 1] != b[n - 1]) {
                a[n - 1] = !a[n - 1];
                a[n] = !a[n];
                if (n + 1 < N) a[n + 1] = !a[n + 1];
                cnt++;
            }
        }
        if (a[N - 1] == b[N - 1])
            answer = Math.min(answer, cnt);
    }
}