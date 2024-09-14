import java.io.*;

public class Main {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			str = br.readLine();
			int i = 0;
			int j = str.length() - 1;
			sb.append(solution(i, j, 0)).append("\n");
		}
		System.out.println(sb);
	}

	private static int solution(int i, int j, int count) {
		while (i <= j) {
			if (str.charAt(i) != str.charAt(j))
				return count == 1 ? 2 : Math.min(solution(i, j - 1, 1), solution(i + 1, j, 1));
			i++;
			j--;
			if (i >= j)
				return count == 0 ? 0 : 1;
		}
		return 2;
	}
}