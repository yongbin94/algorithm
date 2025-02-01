import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		stack.add(-1);
		StringTokenizer st;
		int answer = 0;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int v = Integer.parseInt(st.nextToken());
			while (stack.peek() > v) {
				stack.pop();
			}
			if(stack.peek() == v)
				continue;
			stack.add(v);
			if(v == 0)
				continue;
			answer++;
		}
		System.out.println(answer);
	}
}
