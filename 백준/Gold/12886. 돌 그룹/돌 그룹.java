import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        System.out.println(solution());
    }

    private static int solution() {
        if ((A + B + C) % 3 != 0) return 0;
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        q.offer(A * 1000000 + B * 1000 + C);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (set.contains(u)) continue;
            set.add(u);
            int a = u / 1000000;
            int b = (u / 1000) % 1000;
            int c = u % 1000;
            if (a == b && b == c) return 1;
            q.offer((a > b ? (a - b) * 1000 + b + b : (a + a) * 1000 + b - a) * 1000 + c);
            q.offer((a > c ? (a - c) * 1000000 + c + c : (a + a) * 1000000 + c - a) + b * 1000);
            q.offer((b > c ? (b - c) * 1000 + c + c : (b + b) * 1000 + c - b) + a * 1000000);
        }
        return 0;
    }
}