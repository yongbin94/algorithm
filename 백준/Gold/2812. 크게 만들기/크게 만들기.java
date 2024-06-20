import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		Stack<Integer> stack = new Stack<>();
		stack.push(Integer.MAX_VALUE);
		for(char c : str.toCharArray()) {
			while(K > 0 && stack.peek() < c - '0') {
				stack.pop();
				K--;
			}
			stack.push(c - '0');
		}
		StringBuilder sb = new StringBuilder();
		while(stack.size() > 1) {
			if(K > 0) {
				stack.pop();
				K--;
				continue;
			}
			sb.append(stack.pop());
		}
		System.out.println(sb.reverse());
	}
}
