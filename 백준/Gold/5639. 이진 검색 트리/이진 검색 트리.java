import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Node root = new Node(Integer.parseInt(input));
		while ((input = br.readLine()) != null) {
			root.insert(Integer.parseInt(input));
		}

		StringBuilder sb = new StringBuilder();
		getPostfix(root, sb);
		System.out.print(sb);
	}

	private static class Node {
		int v;
		Node left, right;

		public Node(int v) {
			this.v = v;
		}

		void insert(int n) {
			if (n < this.v) {
				if (this.left == null) this.left = new Node(n);
				else this.left.insert(n);
			} else {
				if (this.right == null) this.right = new Node(n);
				else this.right.insert(n);
			}
		}
	}

	private static void getPostfix(Node node, StringBuilder sb) {
		if (node == null) return;
		getPostfix(node.left, sb);
		getPostfix(node.right, sb);
		sb.append(node.v).append("\n");
	}
}