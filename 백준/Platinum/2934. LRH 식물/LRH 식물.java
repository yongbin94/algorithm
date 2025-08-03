import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		FenwickTree ft = new FenwickTree();
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(ft.query(l, r)).append("\n");
		}
		System.out.println(sb);
	}

	private static class FenwickTree {
		int N = 100_000;
		int[] bit;

		public FenwickTree() {
			bit = new int[N + 1];
		}
		
		public int query(int l, int r) {
			int a = sum(l);
			int b = sum(r);
			add(l, -a);
			add(l + 1, a + 1);
			add(r, -b - 1);
			add(r + 1, b);
			return a + b;
		}
		
		private void add(int i, int v) {
			while(i <= N) {
				bit[i] += v;
				i += i & -i;
			}
		}
		
		private int sum(int i) {
			int res = 0;
			while(i > 0) {
				res += bit[i];
				i -= i & -i;
			}
			return res;
		}
	}
}