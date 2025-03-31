import java.io.*;
import java.util.*;

public class Main {
    static int R, C, N;
    static char[][] map;
    static Set<Integer>[][] set;
    static boolean[][] visited;

    static List<Pos>[] L;
    static List<Integer>[] edges;

    static int[] dfsNum, low;
    static boolean[] isCut;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (R + C + N == 0) break;

            map = new char[R][C];
            visited = new boolean[R][C];
            edges = new ArrayList[N];
            Arrays.setAll(edges, v -> new ArrayList<>());
            set = new HashSet[R][C];
            Arrays.stream(set).forEach(v -> Arrays.setAll(v, w -> new HashSet<>()));
            L = new ArrayList[26];
            Arrays.setAll(L, v -> new ArrayList<>());
            for (int r = 0; r < R; r++) {
                map[r] = br.readLine().toUpperCase().toCharArray();
                for (int c = 0; c < C; c++) {
                    L[map[r][c] - 'A'].add(new Pos(r, c));
                }
            }
            for (int n = 0; n < N; n++) {
                String input = br.readLine().toUpperCase();
                int l = input.length();
                p:
                for (Pos p : L[input.charAt(0) - 'A']) {
                    d:
                    for (int d = 0; d < 8; d++) {
                        if (!isIn(p, d, l)) continue;
                        for (int i = 1; i < l; i++) {
                            int nr = p.r + dr[d] * i;
                            int nc = p.c + dc[d] * i;
                            if (map[nr][nc] != input.charAt(i)) continue d;
                        }
                        for (int i = 0; i < l; i++) {
                            int nr = p.r + dr[d] * i;
                            int nc = p.c + dc[d] * i;
                            for (int s : set[nr][nc]) {
                                edges[n].add(s);
                                edges[s].add(n);
                            }
                            set[nr][nc].add(n);
                        }
                        break p;
                    }
                }
            }
            dfsNum = new int[N];
            low = new int[N];
            isCut = new boolean[N];
            time = 0;

            int connected = 0;
            for (int i = 0; i < N; i++) {
                if (dfsNum[i] == 0) {
                    tarjan(i, -1);
                    connected++;
                }
            }

            boolean articulationFound = false;
            for (int i = 0; i < N; i++) {
                if (isCut[i]) {
                    articulationFound = true;
                    break;
                }
            }

            sb.append(connected == 1 && !articulationFound ? "Yes" : "No").append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {1, 0, -1, 0, 1, -1, -1, 1};
    static int[] dc = {0, 1, 0, -1, -1, -1, 1, 1};

    private static void tarjan(int u, int parent) {
        dfsNum[u] = low[u] = ++time;
        int children = 0;

        for (int v : edges[u]) {
            if (dfsNum[v] == 0) {
                children++;
                tarjan(v, u);
                low[u] = Math.min(low[u], low[v]);
                if (parent != -1 && low[v] >= dfsNum[u]) {
                    isCut[u] = true;
                }
            } else if (v != parent) {
                low[u] = Math.min(low[u], dfsNum[v]);
            }
        }

        if (parent == -1 && children > 1) {
            isCut[u] = true;
        }
    }

    private static boolean isIn(Pos p, int d, int l) {
        int r = p.r + dr[d] * (l - 1);
        int c = p.c + dc[d] * (l - 1);
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}