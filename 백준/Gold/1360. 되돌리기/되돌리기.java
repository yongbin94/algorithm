import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		Arrays.fill(A, Integer.MAX_VALUE);
		A[0] = -1;
		StringBuilder[] S = new StringBuilder[N + 1];
		S[0] = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == 't') {
				S[n] = new StringBuilder(S[n - 1]).append(st.nextToken());
				A[n] = Integer.parseInt(st.nextToken());
			} else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int i = Arrays.binarySearch(A, b - a);
				if (i < 0) i = -i - 2;
				else i--;
                if (i < 0) i = 0;
				S[n] = new StringBuilder(S[i]);
				A[n] = b;
			}
		}
		System.out.println(S[N]);
	}
}