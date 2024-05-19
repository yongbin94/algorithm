import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o2.y - o1.y);
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int time = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            time = time > p.y ? p.y - p.x : time - p.x;
        }
        System.out.println(time < 0 ? -1 : time);
    }
}
