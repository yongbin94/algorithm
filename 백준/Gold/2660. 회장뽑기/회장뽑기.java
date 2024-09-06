import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int n = 0; n < N; n++) {
            Arrays.fill(map[n], INF);
            map[n][n] = 0;
        }
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == -2 && b == -2)
                break;
            map[a][b] = 1;
            map[b][a] = 1;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] > map[i][k] + map[k][j])
                        map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int n = 0; n < N; n++) {
            int tmp = Arrays.stream(map[n]).max().getAsInt();
            if(tmp <= min) {
                if(tmp != min)
                    list = new ArrayList<>();
                min = tmp;
                list.add(n + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(list.size()).append("\n");
        for(int i : list)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}