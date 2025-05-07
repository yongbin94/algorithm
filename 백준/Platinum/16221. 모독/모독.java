import java.io.*;
import java.util.*;

public class Main {
	static final int TREE_SIZE = 1 << 21;
	static final int LEAF_START = (1 << 20) - 1;
	static int[] tree;
	static boolean[] exist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tree = new int[TREE_SIZE];
		exist = new boolean[TREE_SIZE];
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (t == 1) update(k);
			else remove(k);
			sb.append(query()).append("\n");
		}
		System.out.println(sb);
	}

	private static void update(int k) {
		k += LEAF_START;
		while (k >= 1) {
			tree[k]++;
			if (k > LEAF_START)
				exist[k] = true;
			else
				exist[k] = exist[k * 2] & exist[k * 2 + 1];
			k /= 2;
		}
	}

	private static void remove(int k) {
		k += LEAF_START;
		while (k > 1) {
			tree[k]--;
			if (k > LEAF_START)
				exist[k] = tree[k] == 0 ? false : true;
			else
				exist[k] = exist[k * 2] & exist[k * 2 + 1];
			k /= 2;
		}
	}
    
	private static int query() {
		int k = 1, res = 0;
		while(k < LEAF_START) {
			if(exist[k * 2]) {
				res += tree[k * 2];
				k = k * 2 +1;
			}
			else k = k * 2;
		}
		return res;
	}
}