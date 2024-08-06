import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, X, answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        recur(0, 0, 0, Integer.MAX_VALUE, false);
        System.out.println(answer);
    }

    private static void recur(int index, int sum, int max, int min, boolean flag) {
        if (flag && sum >= L && max - min >= X)
            answer++;
        if(index == N)
            return;
        if (sum + A[index] <= R)
            recur(index + 1, sum + A[index], Math.max(max, A[index]), Math.min(min, A[index]), true);
        recur(index + 1, sum, max, min, false);
    }
}