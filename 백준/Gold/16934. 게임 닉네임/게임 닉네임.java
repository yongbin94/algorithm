import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node root = new Node();
		StringBuilder res = new StringBuilder();
		while (N-- > 0) {
			StringBuilder sb = new StringBuilder();
			String input = br.readLine();
			boolean flag = false;
			Node curr = root;
			for (char ch : input.toCharArray()) {
				if (!flag) sb.append(ch);
				if (curr.a[ch - 'a'] == null) {
					flag = true;
					curr.a[ch - 'a'] = new Node();
				}
				curr = curr.a[ch - 'a'];
			}
			curr.cnt++;
			if (curr.cnt > 1) sb.append(curr.cnt);
			res.append(sb).append("\n");
		}
		System.out.println(res);
	}

	private static class Node {
		Node[] a;
		int cnt;

		public Node() {
			a = new Node[26];
			cnt = 0;
		}

	}
}