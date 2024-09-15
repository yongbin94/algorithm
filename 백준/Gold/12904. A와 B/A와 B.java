import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder(br.readLine());

		while (sb.length() > s.length()) {
			if (sb.charAt(sb.length() - 1) == 'A')
				sb.deleteCharAt(sb.length() - 1);
			else {
				sb.deleteCharAt(sb.length() - 1);
				sb.reverse();
			}
		}
		System.out.println(s.equals(sb.toString()) ? 1 : 0);
	}
}
