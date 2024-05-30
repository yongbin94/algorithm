import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> input = new PriorityQueue<>();
        for (int n = 0; n < N; n++)
            input.offer(Integer.parseInt(st.nextToken()));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Integer.MAX_VALUE);
        pq.offer(Integer.MAX_VALUE);
        int prev = input.poll();
        while (!input.isEmpty()) {
            int curr = input.poll();
            int temp = curr - prev - 1;
            prev = curr;
            if (temp > 1) pq.offer(temp / 2);
            if (temp > 0) pq.offer(temp % 2 == 0 ? temp / 2 : temp / 2 + 1);
        }
        int unhappy = 0;
        long answer = 0;
        while (K > 0) {
            unhappy++;
            int size = pq.size();
            K -= size;
            answer += unhappy * size;
            while(pq.peek() <= unhappy)
                pq.poll();
        }
        answer += unhappy * K;
        System.out.println(answer);
    }
}