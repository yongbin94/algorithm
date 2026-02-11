import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(A);
		
		int[] S = new int[N * (N + 1) / 2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				S[idx++] = A[i] + A[j];
			}
		}

		Arrays.sort(S);
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (Arrays.binarySearch(S, A[i] - A[j]) >= 0) {
					System.out.println(A[i]);
					return;
				}
			}
		}
	}
}