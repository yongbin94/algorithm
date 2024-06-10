import java.io.*;
import java.util.*;

public class Main {
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		M += Integer.parseInt(st.nextToken());
		int treeSize = 1;
		while (treeSize <= N * 2)
			treeSize <<= 1;
		int startIndex = treeSize / 2;
		tree = new long[treeSize];
		Arrays.fill(tree, 1);
		for (int n = 0; n < N; n++)
			update(startIndex + n, Integer.parseInt(br.readLine()));

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1"))
				update(startIndex + Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			else
				sb.append(select(startIndex + Integer.parseInt(st.nextToken()) - 1,
						startIndex + Integer.parseInt(st.nextToken()) - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static long select(int i, int j) {
		long result = 1;
		while (i <= j) {
			if (i % 2 == 1)
				result *= tree[i++];
			result %= 1_000_000_007;
			if (j % 2 == 0)
				result *= tree[j--];
			result %= 1_000_000_007;
			i /= 2;
			j /= 2;
		}
		return result;
	}

	private static void update(int i, long v) {
		while (i > 0) {
			tree[i] = v;
			v = ((i % 2 == 0 ? tree[i + 1] : tree[i - 1]) * tree[i]) % 1_000_000_007;
			i /= 2;
		}
	}

}