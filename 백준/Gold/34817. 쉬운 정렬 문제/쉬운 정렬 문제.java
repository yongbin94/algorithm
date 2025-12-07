import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int max = Integer.parseInt(st.nextToken());
		boolean res = true;
		while (st.hasMoreTokens()) {
			int v = Integer.parseInt(st.nextToken());
			if (max - v > K) res = false;
			max = Math.max(max, v);
		}
		System.out.println(res ? "YES" : "NO");
	}
}
