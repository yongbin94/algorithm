import java.awt.Point;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int[][] memo = new int[B.length() + 1][A.length() + 1];
		Point point = new Point(0, 0);
		int max = 0;
		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				memo[j][i] = A.charAt(i - 1) == B.charAt(j - 1) ? memo[j - 1][i - 1] + 1
						: Math.max(memo[j][i - 1], memo[j - 1][i]);
				if (A.charAt(i - 1) == B.charAt(j - 1))
					if (max < memo[j][i]) {
						point = new Point(j, i);
						max = memo[j][i];
					}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (point.x > 0 && point.y > 0) {
			if (B.charAt(point.x - 1) == A.charAt(point.y - 1)) {
				point.y--;
				sb.append(B.charAt(--point.x));
			} else if (memo[point.x][point.y] == memo[point.x][point.y - 1])
				point.y--;
			else if (memo[point.x][point.y] == memo[point.x - 1][point.y])
				point.x--;
		}
		System.out.println(memo[B.length()][A.length()]);
		System.out.println(sb.reverse());
	}
}