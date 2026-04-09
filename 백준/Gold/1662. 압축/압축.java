import java.util.*;

public class Main {
	static String S;
	static int idx = 0;

	public static void main(String[] args) {
		S = new Scanner(System.in).nextLine();
		System.out.println(solve());
	}

	private static int solve() {
		int i = 0;

		while (idx < S.length()) {
			char c = S.charAt(idx++);
			if (c == '(') {
				i--;
				int v = S.charAt(idx - 2) - '0';
				i += v * solve();
			} else if (c == ')') {
				return i;
			} else {
				i++;
			}
		}
		return i;
	}
}