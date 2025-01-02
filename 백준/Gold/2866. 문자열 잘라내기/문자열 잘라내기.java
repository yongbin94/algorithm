import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;
	static String[] T;
	static Set<Integer> S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		T = new String[R];
		for (int r = 0; r < R; r++)
			T[r] = br.readLine();
		for(int n = 1; n < R; n++) {
			S = new HashSet<>();
			for(int c = 0; c < C; c++) {
				int hash = 0;
				for(int r = n; r < R; r++)
					hash = (hash * 31) + T[r].charAt(c) % MOD;
				if(S.contains(hash)) {
					System.out.println(n - 1);
					return;
				}
				S.add(hash);
			}
		}
		System.out.println(R - 1);
	}
}