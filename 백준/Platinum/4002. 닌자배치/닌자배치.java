import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M, answer;
    static Ninja[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        A = new Ninja[N + 1];
        A[0] = new Ninja();
        for (int n = 1; n <= N; n++) {
            A[n] = new Ninja(new StringTokenizer(br.readLine()));
        }
        recur(A[1]);
        System.out.println(answer);
    }

    private static Group recur(Ninja ninja) {
        Group group = new Group(ninja);
        for (Ninja child : ninja.children) {
            group.merge(recur(child));
        }
        answer = Math.max(answer, group.calc());
        return group;
    }

    private static class Ninja {
        long c, l;
        List<Ninja> children = new ArrayList<>();

        public Ninja() {
        }

        public Ninja(StringTokenizer st) {
            A[Integer.parseInt(st.nextToken())].children.add(this);
            this.c = Long.parseLong(st.nextToken());
            this.l = Long.parseLong(st.nextToken());
        }

    }

    private static class Group {
        Ninja manager;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long total;

        public Group(Ninja ninja) {
            manager = ninja;
            pq.offer(ninja.c);
            total = ninja.c;
        }

        public void merge(Group o) {
            if (this.pq.size() < o.pq.size()) {
                PriorityQueue<Long> tmp = this.pq;
                this.pq = o.pq;
                o.pq = tmp;
            }
            this.pq.addAll(o.pq);
            this.total += o.total;
        }

        public long calc() {
            while (total > M) {
                total -= pq.poll();
            }
            return manager.l * pq.size();
        }
    }
}