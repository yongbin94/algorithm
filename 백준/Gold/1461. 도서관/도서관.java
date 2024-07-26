import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> A = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> B = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            int v = Integer.parseInt(st.nextToken());
            if (v > 0)
                A.add(v);
            else
                B.add(v);
        }
        int a = A.isEmpty() ? 0 : A.peek();
        int b = B.isEmpty() ? 0 : B.peek();
        int answer = Math.max(a, b * -1) * -1;
        while (!A.isEmpty()) {
            answer += A.poll() * 2;
            for (int i = 1; i < M; i++) {
                if (A.isEmpty())
                    break;
                A.poll();
            }
        }
        while (!B.isEmpty()) {
            answer += B.poll() * -2;
            for (int i = 1; i < M; i++) {
                if (B.isEmpty())
                    break;
                B.poll();
            }
        }
        System.out.println(answer);
    }
}