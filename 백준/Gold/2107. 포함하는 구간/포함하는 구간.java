import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Interval> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            Interval a = list.get(i);
            int count = 0;
            for (int j = i + 1; j < N; j++) {
                Interval b = list.get(j);
                if (a.e >= b.e)
                    count++;
                else if (a.e < b.s)
                    break;
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }

    private static class Interval implements Comparable<Interval> {
        int s, e;

        public Interval(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Interval o) {
            return this.s != o.s ? this.s - o.s : o.e - this.e;
        }
    }
}