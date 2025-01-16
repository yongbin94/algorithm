import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> A, B;
        A = new PriorityQueue<>((o1, o2) -> o2 - o1);
        B = new PriorityQueue<>();
        int answer = 0;
        while (N-- > 0) {
            int v = Integer.parseInt(br.readLine());
            if (v > 1) A.offer(v);
            else if (v == 1) answer++;
            else B.offer(v);
        }
        while (!A.isEmpty()) {
            if (A.size() == 1) answer += A.poll();
            else answer += (A.poll() * A.poll());
        }
        while (!B.isEmpty()) {
            if (B.size() == 1) answer += B.poll();
            else answer += (B.poll() * B.poll());
        }
        System.out.println(answer);
    }
}