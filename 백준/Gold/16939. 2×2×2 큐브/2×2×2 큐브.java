import java.io.*;
import java.util.*;

public class Main {
	static int[] A;

	public static void main(String[] args) throws IOException {
		A = Arrays.stream(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(solution() ? 1 : 0);
	}

	private static boolean solution() {
		if (check(0, 1, 2, 3) && check(8, 9, 10, 11)) {
			if (check(6, 7, 12, 13) && check(4, 5, 18, 19) && check(16, 17, 22, 23) && check(14, 15, 20, 21)) return true;
			if (check(12, 13, 22, 23) && check(4, 5, 14, 15) && check(6, 7, 16, 17) && check(18, 19, 20, 21)) return true;
		} else if (check(4, 5, 6, 7) && check(20, 21, 22, 23)) {
			if (check(0, 1, 13, 15) && check(8, 9, 12, 14) && check(10, 11, 16, 18) && check(2, 3, 17, 19)) return true;
			if (check(0, 1, 16, 18) && check(2, 3, 12, 14) && check(10, 11, 13, 15) && check(8, 9, 17, 19)) return true;
		} else if (check(12, 13, 14, 15) && check(16, 17, 18, 19)) {
			if (check(0, 2, 5, 7) && check(4, 6, 9, 11) && check(8, 10, 20, 22) && check(1, 3, 21, 23)) return true;
			if (check(0, 2, 20, 22) && check(1, 3, 4, 6) && check(5, 7, 8, 10) && check(9, 11, 21, 23)) return true;
		}
		return false;
	}

	private static boolean check(int a, int b, int c, int d) {
		return A[a] == A[b] && A[b] == A[c] && A[c] == A[d];
	}
}