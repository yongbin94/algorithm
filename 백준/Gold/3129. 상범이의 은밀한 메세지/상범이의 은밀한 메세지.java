import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();

		for (int i = 1; i <= B.length() / 2; i++) {
			outer: for (int j = 0; j < A.length() - B.length() + 1; j++) {
				int[] C = new int[i];
				for (int k = 0; k < i; k++) {
					C[k] = (A.charAt(j + k) - B.charAt(k) + 26) % 26;
				}
				for (int k = i; k < B.length(); k++) {
					if (C[k % i] != (A.charAt(j + k) - B.charAt(k) + 26) % 26) {
						continue outer;
					}
				}
				StringBuilder sb = new StringBuilder();
				for (int k = 0; k < A.length(); k++) {
					sb.append((char) (((A.charAt(k) - 'a') - C[(k + i - (j % i)) % i] + 26) % 26 + 'a'));
				}
				System.out.println(sb);
				return;
			}
		}
	}
}