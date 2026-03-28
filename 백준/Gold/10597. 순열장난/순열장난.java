import java.util.*;

public class Main {
	static String S;
	static int N;
	static boolean[] A;
	static Deque<Integer> stack;

	public static void main(String[] args) {
		S = new Scanner(System.in).nextLine();
		N = S.length() < 10 ? S.length() : (S.length() + 9) / 2;
		A = new boolean[N + 1];
		stack = new ArrayDeque<>();
		recur(0);
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}

	private static boolean recur(int i) {
		if (i == S.length()) return true;
		int v = S.charAt(i) - '0';
		if (!A[v]) {
			A[v] = true;
			if (recur(i + 1)) {
				stack.push(v);
				return true;
			}
			A[v] = false;
		}
		if (i + 1 == S.length()) return false;
		v = v * 10 + S.charAt(i + 1) - '0';
		if (v <= N && !A[v]) {
			A[v] = true;
			if (recur(i + 2)) {
				stack.push(v);
				return true;
			}
			A[v] = false;
		}
		return false;
	}
}
