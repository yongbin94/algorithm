import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
            max = Math.max(max, A[n]);
        }
        cnt = new int[max + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int i = 0;
        while (i < M * 2 - 1) {
            cnt[A[i]]++;
            pq.offer(A[i++]);
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (cnt[pq.peek()] == 0) pq.poll();
            sb.append(pq.peek()).append(" ");
            if (i == N) break;
            if (cnt[A[i]] == 0) pq.offer(A[i]);
            cnt[A[i]]++;
            cnt[A[i - (M * 2) + 1]]--;
            i++;
        }
        System.out.println(sb);
    }
}