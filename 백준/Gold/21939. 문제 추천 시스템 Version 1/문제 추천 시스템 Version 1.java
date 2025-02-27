import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static TreeSet<Problem> problems = new TreeSet<>();
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
            switch (st.nextToken().charAt(0)) {
                case 'r':
                    sb.append(st.nextToken().charAt(0) == '1' ? problems.first().p : problems.last().p).append("\n");
                    break;
                case 'a':
                    problems.add(new Problem(st));
                    break;
                case 's':
                    hashMap.get(Integer.parseInt(st.nextToken())).solved();
                    break;
            }
        }
        System.out.println(sb);
    }


    private static class Problem implements Comparable<Problem> {
        int p, l;

        public Problem(StringTokenizer st) {
            this.p = Integer.parseInt(st.nextToken());
            this.l = Integer.parseInt(st.nextToken());
            hashMap.put(this.p, this);
        }

        public void solved() {
            problems.remove(this);
            hashMap.remove(this.p);
        }

        @Override
        public int compareTo(Problem o) {
            return this.l == o.l ? o.p - this.p : o.l - this.l;
        }
    }
}