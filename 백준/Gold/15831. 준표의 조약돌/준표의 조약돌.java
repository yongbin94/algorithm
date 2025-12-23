import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		int l = 0, b = 0, w = 0, answer = 0;

		for (int r = 0; r < N; r++) {
			if (input.charAt(r) == 'B') b++;
			else w++;

			while (b > B) {
				if (input.charAt(l) == 'B') b--;
				else w--;
				l++;
			}

			if (w >= W) {
				answer = Math.max(answer, r - l + 1);
			}
		}
		System.out.println(answer);
	}
}
