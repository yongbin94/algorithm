import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (b > 10000000) b = 10000000;

		StringBuilder sb = new StringBuilder();

		for (int i = a; i <= b; i++) {
			if (i % 2 == 0) continue;
			if (isPalindrome(i)) {
				if (isPrime(i)) {
					sb.append(i).append("\n");
				}
			}
		}

		sb.append("-1");
		System.out.print(sb);
	}

	private static boolean isPalindrome(int n) {
		int org = n;
		int res = 0;
		while (n > 0) {
			res = res * 10 + (n % 10);
			n /= 10;
		}
		return org == res;
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}