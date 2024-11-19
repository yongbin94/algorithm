import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            A = new long[N + M];
            for (int i = 1; i <= N; i++)
                A[i] = A[i - 1] + Long.parseLong(st.nextToken());
            for (int i = 1; i <= M - 1; i++)
                A[i + N] = (i == 1 && N == M) ? 100_000_000_000L : A[i + N - 1] + A[i] - A[i - 1];
            int answer = 0;
            for(int i = 0; i < N; i++)
                if(A[i + M] - A[i] < K)
                    answer++;
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}