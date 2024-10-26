import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long max = 0;
		while(st.hasMoreTokens()) {
			long v = Long.parseLong(st.nextToken());
			max = Math.max(max, v);
			pq.offer(v);
		}
		
		long orgMax = max * 2, answer = max - pq.peek();
		while(true) {
			long v = pq.poll() * 2;
			max = Math.max(max, v);
			pq.offer(v);
			if(orgMax < v)
				break;
			answer = Math.min(answer, max - pq.peek());
			
		}
		System.out.println(answer);
	}
}