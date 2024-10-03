import java.util.*;

public class Main {
    static int N, M, count;
    static List<Integer> list, answer;
    static boolean[] visited;
    static int[] prev;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        N = sc.nextInt();
        M = sc.nextInt();
        sc.close();

        visited = new boolean[200_000];
        prev = new int[200_000];
        count = 0;
        list = new ArrayList<>();
        answer = new ArrayList<>();

        int n = bfs();

        Stack<Integer> q = new Stack<>();
        while (n >= 0) {
            q.add(n);
            n = prev[n];
            count++;
        }
        while (!q.isEmpty())
            sb.append(q.pop()).append(" ");

        System.out.println(count - 1);
        System.out.println(sb);
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int n = q.poll();
            if (n == M) {
                return n;
            } else {
                if (n < M) {
                    if (!visited[n * 2]) {
                        visited[n * 2] = true;
                        prev[n * 2] = n;
                        q.offer(n * 2);
                    }
                    if (!visited[n + 1]) {
                        visited[n + 1] = true;
                        prev[n + 1] = n;
                        q.offer(n + 1);
                    }
                }
                if (n > 0 && !visited[n - 1]) {
                    visited[n - 1] = true;
                    prev[n - 1] = n;
                    q.offer(n - 1);
                }
            }
        }
        return 0;
    }
}
