import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N + 1];
		int[] B = new int[N + 1];
		Arrays.fill(A, 1001);
		A[0] = 0;
		B[0] = 1001;
		for (int i = 1; i <= N; i++) {
			int v = Integer.parseInt(st.nextToken());
			for (int j = i; j > 0; j--) {
				if (A[j - 1] < v && A[j] > v) {
					A[j] = v;
					B[j] = Math.max(B[j], v);
				}
				if (B[j - 1] > v && B[j] < v) B[j] = v;
			}
		}
		for (int i = N; i > 0; i--) {
			if (A[i] != 1001 || B[i] != 0) {
				System.out.println(i);
				return;
			}
        }
	}
}
