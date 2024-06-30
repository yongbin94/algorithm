import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int[][] memo = new int[B.length() + 1][A.length() + 1];
		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				memo[j][i] = A.charAt(i - 1) == B.charAt(j - 1) ? memo[j - 1][i - 1] + 1
						: Math.max(memo[j][i - 1], memo[j - 1][i]);
			}
		}
		System.out.println(memo[B.length()][A.length()]);
	}
}