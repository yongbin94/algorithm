import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 1; n < N; n++)
            pq.offer(A[n] - A[n - 1]);
        int answer = 0;
        for (int k = 0; k < N - K; k++)
            answer += pq.poll();
        System.out.println(answer);
    }
}