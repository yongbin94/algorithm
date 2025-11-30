import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();
		long[][][][] cnt = new long[3][3][3][3];
		cnt[0][0][0][0] = 1;

		int t = 0, g = 0, f = 0, p = 0;
		long answer = 0;

		for (char c : s) {
			if (c == 'T') t = (t + 1) % 3;
			else if (c == 'G') g = (g + 1) % 3;
			else if (c == 'F') f = (f + 1) % 3;
			else if (c == 'P') p = (p + 1) % 3;
			answer += cnt[t][g][f][p];
			cnt[t][g][f][p]++;
		}
		System.out.println(answer);
	}
}