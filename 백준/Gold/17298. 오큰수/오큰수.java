import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		Stack<Integer> input = new Stack<>();
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> res = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens())
			input.add(Integer.parseInt(st.nextToken()));

		stack.add(Integer.MAX_VALUE);
		while (!input.isEmpty()) {
			int v = input.pop();
			while (stack.peek() <= v)
				stack.pop();
			res.add(stack.peek() == Integer.MAX_VALUE ? -1 : stack.peek());
			stack.add(v);
		}

		StringBuilder sb = new StringBuilder();
		while (!res.isEmpty())
			sb.append(res.pop()).append(" ");
		System.out.println(sb);
	}
}
