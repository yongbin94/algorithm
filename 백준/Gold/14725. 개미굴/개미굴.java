import java.io.*;
import java.util.*;

public class Main {
    static Cave root;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        root = new Cave(null);
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            Cave curr = root;
            while (st.hasMoreTokens()) {
                Cave next = new Cave(st.nextToken());
                if (!curr.child.contains(next))
                    curr.child.add(next);

                curr = curr.child.stream()
                        .filter(c -> c.feed.equals(next.feed))
                        .findFirst()
                        .get();
            }
        }
        for (Cave c : root.child)
            dfs(c, 0);
        System.out.println(sb);
    }

    private static void dfs(Cave cave, int depth) {
        sb.append("--".repeat(depth)).append(cave.feed).append("\n");
        for (Cave c : cave.child)
            dfs(c, depth + 1);
    }

    private static class Cave implements Comparable<Cave> {
        String feed;
        Set<Cave> child;

        public Cave(String feed) {
            this.feed = feed;
            this.child = new TreeSet<>();
        }

        @Override
        public int compareTo(Cave o) {
            return this.feed.compareTo(o.feed);
        }

        @Override
        public boolean equals(Object o) {
            return this.feed.equals(((Cave) o).feed);
        }
    }
}
