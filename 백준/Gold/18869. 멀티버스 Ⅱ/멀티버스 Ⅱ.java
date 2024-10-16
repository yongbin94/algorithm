import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashMap<List<Integer>, Integer> hashMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hashMap = new HashMap<>();
        int answer = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Info> pq = new PriorityQueue<>();
            for (int m = 0; m < M; m++)
                pq.offer(new Info(m, Integer.parseInt(st.nextToken())));
            int[] A = new int[M];
            int prev = pq.peek().v;
            int count = 0;
            while (!pq.isEmpty()) {
                Info info = pq.poll();
                A[info.i] = prev == info.v ? count : ++count;
                prev = info.v;
            }
            List<Integer> list = Arrays.asList(Arrays.stream(A).boxed().toArray(Integer[]::new));
            if (hashMap.containsKey(list))
                answer += hashMap.get(list);
            hashMap.put(list, hashMap.getOrDefault(list, 0) + 1);
        }
        System.out.println(answer);
    }

    private static class Info implements Comparable<Info> {
        int i, v;

        public Info(int i, int v) {
            this.i = i;
            this.v = v;
        }

        @Override
        public int compareTo(Info o) {
            return this.v - o.v;
        }
    }
}