import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                pq.offer(new int[]{Integer.parseInt(st.nextToken()), i});
            }
        }
        int[] A = new int[4];
        int answer = Integer.MAX_VALUE;
        Arrays.fill(A, -1);
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            A[a[1]] = a[0];
            int min = Arrays.stream(A).min().getAsInt();
            int max = Arrays.stream(A).max().getAsInt();
            if (min == -1) continue;
            answer = Math.min(answer, max - min);
        }
        System.out.println(answer);
    }
}