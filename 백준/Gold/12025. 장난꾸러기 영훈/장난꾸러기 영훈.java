import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		long k = Long.parseLong(br.readLine());
		int cnt = 0;
		for (char ch : input.toCharArray()) {
			if (ch == '1' || ch == '2' || ch == '6' || ch == '7') cnt++;
		}
		if (k > (1L << cnt) || k <= 0) {
			System.out.println("-1");
			return;
		}
		k--;
		StringBuilder sb = new StringBuilder();
		for (char ch : input.toCharArray()) {
			if (ch == '1' || ch == '2' || ch == '6' || ch == '7') {
				if (((k >> --cnt) & 1L) == 0) {
					if (ch == '6' || ch == '7') ch -= 5;
				} else {
					if (ch == '1' || ch == '2') ch += 5;
				}
			}
			sb.append(ch);
		}
		System.out.println(sb);
	}
}