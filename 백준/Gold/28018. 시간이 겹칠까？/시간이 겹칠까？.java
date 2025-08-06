import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[1_000_002];
		StringTokenizer st;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			A[Integer.parseInt(st.nextToken())]++;
			A[Integer.parseInt(st.nextToken()) + 1]--;
		}
		for (int i = 1; i <= 1_000_000; i++) {
			A[i] += A[i - 1];
		}
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while (Q-- > 0) {
			sb.append(A[Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb);
	}
}