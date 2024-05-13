import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> input = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.start, o2.start));
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            input.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        PriorityQueue<Integer> room = new PriorityQueue<>();
        room.offer(input.poll().end);
        while(!input.isEmpty()) {
            Lecture lecture = input.poll();
            if(lecture.start >= room.peek())
                room.poll();
            room.offer(lecture.end);
        }
        System.out.println(room.size());
    }

    private static class Lecture {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
