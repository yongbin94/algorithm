import java.util.*;

public class Main {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		recur(N, 1, 2, 3);
		System.out.println(cnt);
		System.out.println(sb);
	}

	private static void recur(int n, int a, int b, int c) {
		if (n == 1) {
			cnt++;
			sb.append(String.format("%d %d\n", a, c));
			return;
		}
		recur(n - 1, a, c, b);
		cnt++;
		sb.append(String.format("%d %d\n", a, c));
		recur(n - 1, b, a, c);
	}
}