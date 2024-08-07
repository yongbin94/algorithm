import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine());
        Arrays.sort(A);
        answer = Integer.MAX_VALUE;
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        int i = 0, j = 0;
        while (j < M && A[j] - A[i] < M)
            j++;
        while (j < N) {
            while(i + 1 <= j && A[j] - A[i + 1] >= M)
                i++;
            if(A[j] - A[i] >= M)
                answer = Math.min(answer, A[j] - A[i]);
            j++;
        }
    }
}
