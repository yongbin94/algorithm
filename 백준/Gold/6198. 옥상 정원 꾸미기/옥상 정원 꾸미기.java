import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		long answer = 0;
		for(int n = 0; n < N; n++) {
			int v = Integer.parseInt(br.readLine());
			while(!stack.isEmpty() && stack.peek() <= v)
				stack.pop();
			answer += stack.size();
			stack.push(v);
		}
		System.out.println(answer);
	}
}
