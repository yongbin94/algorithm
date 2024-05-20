import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Point> input = new PriorityQueue<>((o1,o2) -> o1.x - o2.x);
		PriorityQueue<Point> pq = new PriorityQueue<>((o1,o2) -> o1.y - o2.y); 
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			input.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		while(!input.isEmpty()) {
			Point p = input.poll();
			if(!pq.isEmpty() && pq.peek().y <= p.x)
				pq.poll();
			pq.offer(p);
		}
		System.out.println(pq.size());
	}
}
