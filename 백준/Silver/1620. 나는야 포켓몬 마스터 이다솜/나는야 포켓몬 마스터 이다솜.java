import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add(null);
		for (int n = 1; n <= N; n++) {
			String str = br.readLine();
			map.put(str, n);
			list.add(str);
		}
		while (M-- > 0) {
			String str = br.readLine();
			sb.append(Character.isDigit(str.charAt(0)) ? list.get(Integer.parseInt(str)) : map.get(str)).append("\n");
		}
		System.out.println(sb);
	}
}
