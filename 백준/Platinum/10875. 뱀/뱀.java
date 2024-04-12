import java.util.*;
import java.io.*;

public class Main {
	static int L, R, C, T, D;
	static long result;
	static List<Snake> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		L = Integer.parseInt(br.readLine()) + 1;
		list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		while (true) {
			st = new StringTokenizer(N-- != 0 ? br.readLine() : Integer.toString(L * 2));
			T = Integer.parseInt(st.nextToken());
			if (play())
				break;
			D = (D + (st.nextToken().charAt(0) == 'R' ? 1 : 3)) % 4;
		}
		System.out.println(result);
	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static boolean play() {
		int d = D % 2;
		int start = d == 0 ? C : R;
		int len = d == 0 ? T * dc[D] : T * dr[D];
		int end = d == 0 ? C + len : R + len;
		int point = d == 0 ? R : C;
		int min = Integer.MAX_VALUE;
		for (Snake s : list) {
			if (s.d == d) {
				if (s.p != point)
					continue;
				if (len > 0 ? (start < s.s && s.s <= end) : (start > s.e && s.e >= end))
					min = Math.min(min, len > 0 ? s.s - start : start - s.e);

			} else {
				if (s.s > point || s.e < point)
					continue;
				if (len > 0 ? (start < s.p && s.p <= end) : (start > s.p && s.p >= end))
					min = Math.min(min, len > 0 ? s.p - start : start - s.p);
			}
		}
		if (min != Integer.MAX_VALUE) {
			result += min;
			return true;
		}
		if (end < L * -1) {
			result += Math.abs(start - L * -1);
			return true;
		}
		if (end >= L) {
			result += Math.abs(L - start);
			return true;
		}
		list.add(start < end ? new Snake(point, d, start, end) : new Snake(point, d, end, start));
		if (d == 0)
			C += len;
		else
			R += len;
		result += Math.abs(len);
		return false;
	}

	private static class Snake {
		int p, d, s, e;

		public Snake(int p, int d, int s, int e) {
			this.p = p;
			this.d = d;
			this.s = s;
			this.e = e;
		}
	}
}
