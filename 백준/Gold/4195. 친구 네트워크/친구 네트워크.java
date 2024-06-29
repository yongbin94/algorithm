import java.io.*;
import java.util.*;

public class Main {
	static Map<String, Integer> cnt;
	static Map<String, String> parent;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			cnt = new HashMap<>();
			parent = new HashMap<>();
			int N = Integer.parseInt(br.readLine());
			while (N-- > 0) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				union(a, b);
			}
		}
		System.out.println(sb);
	}

	private static void union(String a, String b) {
		String rootA = find(a);
		String rootB = find(b);

		if (!parent.containsKey(a)) {
			parent.put(a, a);
		}

		if (!rootA.equals(rootB)) {
			parent.put(rootB, rootA);
			cnt.put(rootA, cnt.getOrDefault(rootA, 1) + cnt.getOrDefault(rootB, 0));
		}
		sb.append(cnt.get(rootA)).append("\n");
	}

	private static String find(String a) {
		if (!parent.containsKey(a)) {
			parent.put(a, a);
			cnt.put(a, 1);
			return a;
		}

		String root = a;
		while (!root.equals(parent.get(root))) {
			root = parent.get(root);
		}

		while (!a.equals(root)) {
			String next = parent.get(a);
			parent.put(a, root);
			a = next;
		}

		return root;
	}
}
