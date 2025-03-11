import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        long answer = 0, curr = 0;
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            if(a[1] <= curr) continue;
            curr = Math.max(curr, a[0]);
            long cnt = (a[1] - curr - 1) / L + 1;
            curr = curr + L * cnt;
            answer += cnt;
        }
        System.out.println(answer);
    }
}