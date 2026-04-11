import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String S = br.readLine();
		int res = N;
		for (int K = 1; K <= N; K++) {
			int[] A = new int[N];
			int max = 0;
			int total = 0;

			for (int i = 0; i < N; i++) {
				if (S.charAt(i) == '#') {
					if (i >= K && A[i - K] > 0) {
						A[i - K]--;
						A[i]++;
					} else {
						total++;
						A[i]++;
					}
				}
			}
			res = Math.min(res, total);
		}
		System.out.println(res);
	}
}