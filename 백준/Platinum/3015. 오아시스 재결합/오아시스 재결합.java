import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long result = 0;
		Stack<Person> stack = new Stack<>();
		stack.push(new Person(Integer.parseInt(br.readLine()), 1));
		while (--N > 0) {
			int h = Integer.parseInt(br.readLine());
			Person p = stack.pop();
			while (p.height < h) {
				result += p.cnt;
				if(stack.isEmpty()) break;
				p = stack.pop();
			}
			if (p.height == h) {
				result += stack.isEmpty() ? p.cnt++ : ++p.cnt;
				stack.push(p);
			} else {
				if (p.height > h) {
					stack.push(p);
					result += 1;
				}
				stack.push(new Person(h, 1));
			}
		}
		System.out.println(result);
	}

	private static class Person {
		int height, cnt;

		public Person(int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
	}
}
