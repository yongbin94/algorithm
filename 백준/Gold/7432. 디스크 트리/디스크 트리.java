import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node root = new Node();
		while (N-- > 0) {
			root.insert(new StringTokenizer(br.readLine(), "\\"));
		}
		root.print(0);
		System.out.println(sb);
	}

	private static class Node {
		TreeMap<String, Node> children;

		public Node() {
			children = new TreeMap<>();
		}

		public void insert(StringTokenizer st) {
			Node now = this;
			while (st.hasMoreTokens()) {
				String name = st.nextToken();
				now.children.putIfAbsent(name, new Node());
				now = now.children.get(name);
			}
		}

		public void print(int depth) {
			for (String child : children.keySet()) {
				sb.append(" ".repeat(depth)).append(child).append("\n");
				children.get(child).print(depth + 1);
			}
		}
	}
}