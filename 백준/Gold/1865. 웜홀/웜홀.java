import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int[][] map;
    static int N;
    static List<Edge> list;
    static List<Integer> warp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()), tc = 0;
        while (tc++ < T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int n = 0; n < N; n++) {
                Arrays.fill(map[n], INF);
                map[n][n] = 0;
            }
            list = new ArrayList<>();
            warp = new ArrayList<>();
            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken()) * (i < M ? 1 : -1);
                if(i < M)
                    list.add(new Edge(e, s, w));
                else
                    warp.add(e);
                list.add(new Edge(s, e, w));
                map[s][e] = w;
            }
            solution();
            boolean flag = false;
            for(int n = 0; n <N; n++) {
                if (map[n][n] < 0) {
                    flag = true;
                    break;
                }
            }
            sb.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static void solution() {
        for (int k = 0; k < N; k++) {
            for (Edge edge : list) {
                for (int n : warp) {
                    if (map[n][edge.s] == INF)
                        continue;
                    map[n][edge.e] = Math.min(map[n][edge.e], map[n][edge.s] + edge.w);
                }
            }
        }
    }


    private static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
