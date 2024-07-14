import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        HashMap<String, Friend> hs = new HashMap<>();

        PriorityQueue<Friend> pq = new PriorityQueue<>();
        while (N-- > 0) {
            String name = br.readLine();
            Friend max = new Friend("", -1, 0, 0, 0);
            int strig = 0, freeze = 0, start = 0, fail = 0, freeze_tmp = 0;
            String[] map = new String[7];
            for (int i = 0; i < 7; i++)
                map[i] = br.readLine();
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < 7; j++) {
                    if (map[j].charAt(i) == 'X') {
                        Friend tmp = new Friend(name, strig, freeze, start, 0);
                        if (max.compareTo(tmp) > 0)
                            max = tmp;
                        strig = 0;
                        freeze = 0;
                        freeze_tmp = 0;
                        fail++;
                        continue;
                    }
                    if (strig == 0)
                        start = i * 7 + j;
                    else if (map[j].charAt(i) == 'F') {
                        freeze_tmp++;
                        continue;
                    }
                    if (map[j].charAt(i) == 'O') {
                        freeze += freeze_tmp;
                        freeze_tmp = 0;
                        strig++;
                    }
                }
            }
            Friend tmp = new Friend(name, strig, freeze, start, 0);
            if (max.compareTo(tmp) > 0)
                max = tmp;
            max.fail = fail;
            pq.offer(max);
        }
        StringBuilder sb = new StringBuilder();
        Friend prev = new Friend("",0,0,0,0);
        int rank = 0;
        for (int i = 1, size = pq.size(); i <= size; i++) {
            Friend curr = pq.poll();
            String name = curr.name;
            curr.name = "";
            if(prev.compareTo(curr) != 0)
                rank++;
            prev = curr;
            sb.append(rank).append(". ").append(name).append("\n");
        }
        System.out.println(sb);
    }

    private static class Friend implements Comparable<Friend> {
        String name;
        int strig, freeze, start, fail;

        public Friend(String name, int strig, int freeze, int start, int fail) {
            this.name = name;
            this.strig = strig;
            this.freeze = freeze;
            this.start = start;
            this.fail = fail;
        }

        @Override
        public int compareTo(Friend o) {
            return this.strig != o.strig ? o.strig - this.strig
                    : this.freeze != o.freeze ? this.freeze - o.freeze
                    : this.start != o.start ? this.start - o.start
                    : this.fail != o.fail ? this.fail - o.fail
                    : this.name.compareTo(o.name);
        }
    }
}
