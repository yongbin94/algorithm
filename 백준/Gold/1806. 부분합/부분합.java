import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++)
            A[n] = A[n - 1] + Integer.parseInt(st.nextToken());

        int i = 0, j = 1, answer = Integer.MAX_VALUE;
        while (j <= N) {
            if (A[j] - A[i] >= S)
                answer = Math.min(answer, j - i++);
            else
                j++;
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}