import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		long[] T = new long[N + 1];
		T[0] = 1;
		T[1] = 1;
		for (int i = 2; i <= N; i++) {
			T[i] = T[i - 1] + 2 * T[i - 2];
		}	

		long v = N % 2 == 1 ? T[(N - 1) / 2] : T[N / 2] + 2 * T[N / 2 - 1];
		System.out.println((T[N] + v) / 2);
	}
}
