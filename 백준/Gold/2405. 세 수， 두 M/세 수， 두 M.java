import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] A = new long[N];

		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(A);

		long res = 0;
		for (int i = 1; i < N - 1; i++) {
			long a = Math.abs(2 * A[i] - A[0] - A[i + 1]);
			long b = Math.abs(A[i - 1] + A[N - 1] - 2 * A[i]);

			res = Math.max(res, Math.max(a, b));
		}

		System.out.println(res);
	}
}