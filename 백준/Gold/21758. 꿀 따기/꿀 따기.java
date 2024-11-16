import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] A, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        S = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        A[0] = Long.parseLong(st.nextToken());
        S[0] = A[0];
        for (int n = 1; n < N; n++) {
            A[n] = Long.parseLong(st.nextToken());
            S[n] = S[n - 1] + A[n];
        }
        long answer = 0;
        for (int i = 1; i < N - 1; i++) {
            answer = Math.max(answer, S[N - 1] * 2 - A[0] - A[i] - S[i]);
            answer = Math.max(answer, S[N - 2] - A[i] + S[i - 1]);
            answer = Math.max(answer, S[N - 2] - A[0] + A[i]);
        }
        System.out.println(answer);
    }
}