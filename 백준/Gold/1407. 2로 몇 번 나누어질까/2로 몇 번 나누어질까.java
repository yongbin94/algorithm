import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		System.out.println(get(B) - get(A - 1));
	}

	private static long get(long n) {
		if (n <= 0) return 0;
		long res = n;
		long d = 2;
		long c = 1;
		while (d <= n) {
			res += c * (n / d);
			c <<= 1;
			d <<= 1;
		}
		return res;
	}
}