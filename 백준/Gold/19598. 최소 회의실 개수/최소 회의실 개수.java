import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        StringTokenizer st;
        while (N-- > 0)  {
            st= new StringTokenizer(br.readLine());
            pq.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        PriorityQueue<Integer> room = new PriorityQueue<>();
        int answer = 0;
        while(!pq.isEmpty()) {
            Meeting m = pq.poll();
            while(!room.isEmpty() && room.peek() <= m.s)
                room.poll();
            room.offer(m.e);
            answer = Math.max(answer, room.size());
        }
        System.out.println(answer);
    }

    private static class Meeting implements Comparable<Meeting> {
        int s, e;

        public Meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.s - o.s;
        }
    }
}