import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static Group[] groups;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        groups = new Group[N + 1];
        Arrays.setAll(groups, v -> new Group());
        for (int n = 1; n <= N; n++) {
            groups[n].member.add(n);
        }
        dist = new int[N + 1][N + 1];
        Arrays.stream(dist).forEach(v -> Arrays.fill(v, INF));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (groups[a].member.size() < groups[a].member.size()) {
                groups[0] = groups[a];
                groups[a] = groups[b];
                groups[b] = groups[0];
            }
            groups[a].merge(groups[b]);
            groups[b] = groups[a];
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> leader = new TreeSet<>();
        for (int n = 1; n <= N; n++) {
            if (groups[n].used)
                continue;
            leader.add(groups[n].getLeader());
        }
        sb.append(leader.size()).append("\n");
        for (int v : leader) {
            sb.append(v).append("\n");
        }
        System.out.println(sb);
    }

    private static class Group {
        Set<Integer> member = new HashSet<>();
        boolean used = false;


        public void merge(Group o) {
            this.member.addAll(o.member);
            for (int i : member) {
                groups[i] = this;
            }
        }

        public int getLeader() {
            List<Integer> list = new ArrayList<>(member);
            for (int k : list) {
                for (int i : list) {
                    for (int j : list) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int res = 0;
            int min = INF;
            for (int i : list) {
                int max = 0;
                for (int j : list) {
                    if (i == j) continue;
                    max = Math.max(max, dist[i][j]);
                }
                if (min > max) {
                    min = max;
                    res = i;
                }
            }
            used = true;
            return res;
        }
    }
}