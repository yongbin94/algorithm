import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pos J = null;
        List<Pos>[] L = new ArrayList[2];
        Arrays.setAll(L, v -> new ArrayList<>());
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 1) L[(r + c) % 2].add(new Pos(r, c));
                if (v == 2) J = new Pos(r, c);
            }
        }
        int j = (J.r + J.c) % 2;
        if (!L[(j + 1) % 2].isEmpty()) {
            System.out.println("Shorei");
            return;
        }
        visited = new boolean[L[j].size()];
        answer = Integer.MAX_VALUE;
        recur(L[j], J, 0, 0);
        
        System.out.println("Undertaker");
        System.out.println(answer);
    }

    private static void recur(List<Pos> list, Pos p, int cnt, int move) {
        if (list.size() == cnt) {
            answer = Math.min(answer, move);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            Pos c = list.get(i);
            recur(list, c, cnt + 1, move + c.getDistance(p));
            visited[i] = false;
        }
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getDistance(Pos p) {
            return Math.max(Math.abs(this.r - p.r), Math.abs(this.c - p.c));
        }
    }
}