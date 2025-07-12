import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] A;

    static {
        A = new ArrayList[11];
        A[0] = new ArrayList<>(List.of(0));
        for (int g = 1; g <= 10; g++) {
            A[g] = new ArrayList<>(A[g - 1]);
            for (int i = A[g - 1].size() - 1; i >= 0; i--) {
                A[g].add((A[g - 1].get(i) + 1) & 3);
            }
        }
    }

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[101][101];
        while (N-- > 0) {
            play(new StringTokenizer(br.readLine()));
        }
        int answer = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (visited[y][x] && visited[y][x + 1] && visited[y + 1][x] && visited[y + 1][x + 1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    private static void play(StringTokenizer st) {
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        visited[y][x] = true;
        for (int dir : A[g]) {
            x += dx[(dir + d) & 3];
            y += dy[(dir + d) & 3];
            visited[y][x] = true;
        }
    }
}
