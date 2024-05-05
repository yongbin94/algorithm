import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static final int M = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Point> input = new PriorityQueue<>((o1, o2) -> o1.x - o2.x);
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			input.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int index = -1, prev = Integer.MIN_VALUE;
		PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o2.y - o1.y);
		while (!input.isEmpty()) {
			Point p = input.poll();
			pq.offer(new Point(prev == p.x ? index : ++index, p.y));
			prev = p.x;
		}
		int treeSize = 1;
		while (treeSize <= index * 2)
			treeSize <<= 1;
		tree = new int[treeSize + 1];
		int startIndex = treeSize / 2;
		int endIndex = startIndex + index;
		Queue<Point> q = new ArrayDeque<>();
		long answer = 0;
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			if (!q.isEmpty() && q.peek().y != p.y)
				while (!q.isEmpty())
					update(startIndex + q.poll().x);
			int left = select(startIndex, startIndex + p.x - 1);
			int rigth = select(startIndex + p.x + 1, endIndex);
			q.offer(p);
			answer += ((long)left * rigth) % M;
		}
		System.out.println(answer % M);
	}

	private static int select(int i, int j) {
		int result = 0;
		while (i <= j) {
			if (i % 2 == 1)
				result += tree[i++];
			if (j % 2 == 0)
				result += tree[j--];
			i /= 2;
			j /= 2;
		}
		return result;
	}

	private static void update(int i) {
		while (i > 0) {
			tree[i]++;
			i /= 2;
		}
	}
}
