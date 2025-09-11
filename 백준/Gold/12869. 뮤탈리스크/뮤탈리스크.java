import java.io.*;
import java.util.*;

public class Main {
    static int N, A;
    static int[][] data = {{9}, {903, 309}, {90301, 90103, 30901, 30109, 10903, 10309}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A *= 100;
            A += Integer.parseInt(st.nextToken());
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        Set<Integer> visited = new HashSet<>();
        int answer = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int v = q.poll();
                if (isCorrect(v)) {
                    System.out.println(answer);
                    return;
                }
                for (int d : data[N - 1]) {
                    int nv = v + d;
                    if (visited.contains(nv)) continue;
                    visited.add(nv);
                    q.offer(nv);
                }
            }
            answer++;
        }
    }

    private static boolean isCorrect(int b) {
        int a = A;
        for (int n = 0; n < N; n++) {
            if (a % 100 > b % 100) return false;
            a /= 100;
            b /= 100;
        }
        return true;
    }
}