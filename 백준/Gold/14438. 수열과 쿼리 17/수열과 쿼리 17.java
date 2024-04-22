import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int len = N;
		int treeHeight = 0;
		while (len != 0) {
			len /= 2;
			treeHeight++;
		}
		int treeSize = (int) Math.pow(2, treeHeight + 1);
		int startIndex = treeSize / 2 - 1;
		tree = new int[treeSize + 1];
		Arrays.fill(tree, Integer.MAX_VALUE);
		st = new StringTokenizer(br.readLine());
		for (int i = startIndex + 1; i <= startIndex + N; i++)
			tree[i] = Integer.parseInt(st.nextToken());
		setTree(treeSize);
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1"))
				update(startIndex + Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			else
				select(startIndex + Integer.parseInt(st.nextToken()), startIndex + Integer.parseInt(st.nextToken()));
		}
		System.out.println(sb);
	}

	private static void setTree(int i) {
		while (i > 1) {
			tree[i / 2] = Math.min(tree[i / 2], tree[i]);
			i--;
		}
	}

	private static void update(int i, int v) {
		while (i > 0) {
			tree[i] = i * 2 >= tree.length - 1 ? v : Math.min(tree[i * 2], tree[i * 2 + 1]);
			i /= 2;
		}
	}

	private static void select(int s, int e) {
		int min = Integer.MAX_VALUE;
		while (s <= e) {
			if (s % 2 == 1) {
				min = Math.min(min, tree[s]);
				s++;
			}
			if (e % 2 == 0) {
				min = Math.min(min, tree[e]);
				e--;
			}
			s /= 2;
			e /= 2;
		}
		sb.append(min).append("\n");
	}
}