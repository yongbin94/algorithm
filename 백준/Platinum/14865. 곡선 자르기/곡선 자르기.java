import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Info implements Comparable<Info> {
		int a, b;

		public Info(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Info o) {
			return this.a - o.a;
		}

	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Info> list = new ArrayList<>();
		Queue<Pos> q = new ArrayDeque<>();
		StringTokenizer st;
		int n = 0;
		int x = 0;
		int y = -1;
		while (true) {
			n++;
			st = new StringTokenizer(br.readLine());
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());
			q.add(new Pos(nx, ny));
			if (ny <= 0)
				break;
		}
		while (n++ < N) {
			st = new StringTokenizer(br.readLine());
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());
			if (y <= 0 && ny > 0) {
				x = nx;
				y = ny;
			}
			if (y > 0 && ny <= 0) {
				list.add(new Info(Math.min(x, nx), Math.max(x, nx)));
				x = nx;
				y = ny;
			}
		}
		while (!q.isEmpty()) {
			Pos p = q.poll();
			int nx = p.x;
			int ny = p.y;
			if (y <= 0 && ny > 0) {
				x = nx;
				y = ny;
			}
			if (y > 0 && ny <= 0) {
				list.add(new Info(Math.min(x, nx), Math.max(x, nx)));
				x = nx;
				y = ny;
			}
		}

		int A = 0;
		int B = 0;
		Collections.sort(list);
		int b = Integer.MIN_VALUE;
		int b2 = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			Info aaa = list.get(i);
			if(b < aaa.b) {
				b = aaa.b;
				A++;
			}
			if(b2 < aaa.b)
				B++;
			b2= aaa.b;
		}
		System.out.println(A + " " + B);
	}
}
