import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[K];
		int[] B = new int[K];
		String[] S = new String[N];

		String input = br.readLine();
		for (int k = 0; k < K; k++) {
			A[k] = input.charAt(k) - 'A';
			B[k] = k;
		}

		int questionIdx = 0;
		for (int n = 0; n < N; n++) {
			S[n] = br.readLine();
			if (S[n].charAt(0) == '?') questionIdx = n;
		}

		for (int n = 0; n < questionIdx; n++) {
			for (int k = 0; k < K - 1; k++) {
				if (S[n].charAt(k) == '-') {
					int tmp = B[k];
					B[k] = B[k + 1];
					B[k + 1] = tmp;
				}
			}
		}

		for (int n = N - 1; n > questionIdx; n--) {
			for (int k = 0; k < K - 1; k++) {
				if (S[n].charAt(k) == '-') {
					int tmp = A[k];
					A[k] = A[k + 1];
					A[k + 1] = tmp;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < K - 1; k++) {
			if (B[k] == A[k]) {
				sb.append('*');
			} else if (B[k] == A[k + 1] && B[k + 1] == A[k]) {
				sb.append('-');
				int tmp = B[k];
				B[k] = B[k + 1];
				B[k + 1] = tmp;
			} else {
				System.out.println("x".repeat(K - 1));
				return;
			}
		}

		System.out.println(sb);
	}
}