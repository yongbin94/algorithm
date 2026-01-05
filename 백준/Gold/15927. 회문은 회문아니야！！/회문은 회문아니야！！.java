import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int res = -1;
		for (int i = 0, j = S.length() - 1; i < S.length() / 2; i++) {
			if (S.charAt(i) != S.charAt(j--)) {
				System.out.println(S.length());
				return;
			} 
			if (S.charAt(i) != S.charAt(i + 1)) {
				res = S.length() - 1;
			}
		}
		System.out.println(res);
	}
}
