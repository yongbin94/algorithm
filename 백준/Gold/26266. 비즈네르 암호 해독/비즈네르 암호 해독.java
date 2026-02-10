import java.io.*;

public class Main {
	static int L;
	static int[] key;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		L = A.length();

		key = new int[L + 1];
		for (int i = 1; i <= L; i++) {
			int a = A.charAt(i - 1) - 'A' + 1;
			int b = B.charAt(i - 1) - 'A' + 1;
			int k = b - a;
			key[i] = k > 0 ? k : k + 26;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int len = 1; len <= L; len++) {
			sb.append((char) (key[len] + 'A' - 1));
			if (L % len != 0) continue;
			if (isValid(len)) {
				System.out.println(sb);
				return;
			}
		}
	}

	private static boolean isValid(int len) {
		for (int i = 1; i <= len; i++) {
			for (int j = i + len; j <= L; j += len) {
				if (key[i] != key[j]) return false;
			}
		}
		return true;
	}
}