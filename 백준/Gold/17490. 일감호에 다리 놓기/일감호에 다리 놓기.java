import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		int[] A = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		boolean[] B = new boolean[N + 2];
		for (int n = 1; n <= N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1 && b == N) a = N + 1;
			if (b == 1 && a == N) b = N + 1;
			B[a > b ? a : b] = true;
		}
		if (M < 2) {
			System.out.println("YES");
			return;
		}
		int end = N + 1;
		int min = Integer.MAX_VALUE;
		while (!B[end]) {
			min = Math.min(min, A[--end]); 
		}
		for (int n = 1; n <= end; n++) {
			if(B[n]) {
				K -= min;
				if(K < 0) {
					System.out.println("NO");
					return;
				}
				min = Integer.MAX_VALUE;
			}
			min = Math.min(min, A[n]);
		} 
		System.out.println("YES");
	}
}