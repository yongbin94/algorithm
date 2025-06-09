import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            people.add(new Person(new StringTokenizer(br.readLine())));
        }
        Collections.sort(people);
        int D = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (Person p : people) {
            pq.offer(p.s);
            while (!pq.isEmpty() && p.e - pq.peek() > D) {
                pq.poll();
            }
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }

    private static class Person implements Comparable<Person> {
        int s, e;

        public Person(StringTokenizer st) {
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            this.s = Math.min(a, b);
            this.e = Math.max(a, b);
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.e, o.e);
        }
    }
}