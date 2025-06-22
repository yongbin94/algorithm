import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.nextLine();
		String B = sc.nextLine();
		int[] cntA = new int[A.length() + 1];
		int[] cntB = new int[B.length() + 1];
		for (int i = 0, v = 0; i < A.length(); i++) {
			v += A.charAt(i) == '(' ? 1 : -1;
			if (v < 0) break;
			cntA[v]++;
		}
		for (int i = 0, v = 0, w = 0; i < B.length(); i++) {
			if (B.charAt(i) == ')') {
				w = Math.max(0, w - 1);
				if (++v < 0) continue;
				if (w == 0) cntB[v]++;
			} else {
				v--;
				w++;
			}
		}
		long res = 0;
		for (int i = 0; i <= Math.min(A.length(), B.length()); i++) {
			res += (long) cntA[i] * cntB[i];
		}
		System.out.println(res);
	}
}