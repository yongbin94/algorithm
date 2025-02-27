import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static TreeSet<Problem> problems = new TreeSet<>();
    static HashMap<Integer, TreeSet<Problem>> groups = new HashMap<>();
    static HashMap<Integer, Problem> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            problems.add(new Problem(new StringTokenizer(br.readLine())));
        }
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "recommend":
                    int g = Integer.parseInt(st.nextToken());
                    sb.append(st.nextToken().charAt(0) == '1' ? groups.get(g).first().p : groups.get(g).last().p).append("\n");
                    break;
                case "recommend2":
                    sb.append(st.nextToken().charAt(0) == '1' ? problems.first().p : problems.last().p).append("\n");
                    break;
                case "recommend3":
                    int x = Integer.parseInt(st.nextToken());
                    SortedSet<Problem> res = x == 1
                            ? problems.headSet(new Problem(Integer.parseInt(st.nextToken())))
                            : problems.tailSet(new Problem(Integer.parseInt(st.nextToken())));
                    sb.append(res.isEmpty() ? -1 : x == 1 ? res.last().p : res.first().p).append("\n");
                    break;
                case "add":
                    problems.add(new Problem(st));
                    break;
                case "solved":
                    hashMap.get(Integer.parseInt(st.nextToken())).solved();
                    break;
            }
        }
        System.out.println(sb);
    }

    private static class Problem implements Comparable<Problem> {
        int p, l, g;

        public Problem(StringTokenizer st) {
            this.p = Integer.parseInt(st.nextToken());
            this.l = Integer.parseInt(st.nextToken());
            this.g = Integer.parseInt(st.nextToken());
            hashMap.put(this.p, this);
            groups.computeIfAbsent(g, v -> new TreeSet<>()).add(this);
        }

        public Problem(int l) {
            this.p = 0;
            this.l = l;
            this.g = 0;
        }

        public void solved() {
            problems.remove(this);
            groups.get(g).remove(this);
            hashMap.remove(this.p);
        }

        @Override
        public int compareTo(Problem o) {
            return this.l == o.l ? o.p - this.p : o.l - this.l;
        }
    }
}
