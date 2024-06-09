import java.io.*;
import java.util.*;

public class Main {
	static int[] maxTree, minTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int treeSize = 1;
		while (treeSize <= N * 2)
			treeSize <<= 1;
		int startIndex = treeSize / 2;
		minTree = new int[treeSize];
		Arrays.fill(minTree, Integer.MAX_VALUE);
		maxTree = new int[treeSize];

		for (int n = 0; n < N; n++) {
			int v = Integer.parseInt(br.readLine());
			updateMin(startIndex + n, v);
			updateMax(startIndex + n, v);
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			sb.append(selectMin(startIndex + i - 1, startIndex + j - 1)).append(" ");
			sb.append(selectMax(startIndex + i - 1, startIndex + j - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static void updateMax(int i, int v) {
		while (i > 0) {
			maxTree[i] = Math.max(maxTree[i], v);
			i /= 2;
		}
	}

	private static void updateMin(int i, int v) {
		while (i > 0) {
			minTree[i] = Math.min(minTree[i], v);
			i /= 2;
		}
	}

	private static int selectMax(int i, int j) {
		int result = 0;
		while (i <= j) {
			if (i % 2 == 1)
				result = Math.max(result, maxTree[i++]);
			if (j % 2 == 0)
				result = Math.max(result, maxTree[j--]);
			i /= 2;
			j /= 2;
		}
		return result;
	}

	private static int selectMin(int i, int j) {
		int result = Integer.MAX_VALUE;
		while (i <= j) {
			if (i % 2 == 1)
				result = Math.min(result, minTree[i++]);
			if (j % 2 == 0)
				result = Math.min(result, minTree[j--]);
			i /= 2;
			j /= 2;
		}
		return result;
	}
}