import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		int i = 0, answer = 0;
		while (i < P.length()) {
			answer++;
			int max = 0;
			for (int j = 0; j < S.length(); j++) {
				if (P.charAt(i) != S.charAt(j)) continue;
				for (int k = 1; k < Math.min(P.length() - i, S.length() - j); k++) {
					if (P.charAt(i + k) != S.charAt(j + k)) break;
					max = Math.max(max, k);
				}
			}
			i += max + 1;
		}
		System.out.println(answer);
	}
}
