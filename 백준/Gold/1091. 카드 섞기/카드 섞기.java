import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] P = new int[N];
		int[] S = new int[N];
		int[] A = new int[N];
		int[] B = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
			A[i] = i;
			B[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0;
		while (true) {
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				if (P[A[i]] != i % 3) {
					flag = false;
					break;
				}
			}

			if (flag) {
				System.out.println(count);
				return;
			}

			int[] next = new int[N];
			for (int i = 0; i < N; i++) {
				next[S[i]] = A[i];
			}
			A = next;
			count++;

			flag = true;
			for (int i = 0; i < N; i++) {
				if (A[i] != B[i]) {
					flag = false;
					break;
				}
			}
			if (flag) break;
		}
		System.out.println(-1);
	}
}