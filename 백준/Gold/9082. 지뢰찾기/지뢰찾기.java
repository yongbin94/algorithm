import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N + 2];
			char[] B = new char[N + 2];
			String a = br.readLine();
			String b = br.readLine();
			for (int n = 1; n <= N; n++) {
				A[n] = a.charAt(n - 1) - '0';
				B[n] = b.charAt(n - 1);
			}
			B[0] = '-';
			B[N + 1] = '-';
			for (int i = 0; i <= N; i++) {
				if (i == N) {
					for (int j = 1; j <= N; j++) {
						if (B[j] == '#') {
							B[j] = '*';
							break;
						}
					}
				}
				for (int n = 1; n <= N; n++) {
					if (A[n] == 0) {
						B[n - 1] = '-';
						B[n] = '-';
						B[n + 1] = '-';
					} else if (A[n] == 1) {
						if (B[n - 1] == '*') {
							B[n] = '-';
							B[n + 1] = '-';
						} else if (B[n] == '*') {
							B[n - 1] = '-';
							B[n + 1] = '-';
						} else if (B[n + 1] == '*') {
							B[n - 1] = '-';
							B[n] = '-';
						} else if (B[n - 1] == '-' && B[n] == '-') {
							B[n + 1] = '*';
						} else if (B[n - 1] == '-' && B[n + 1] == '-') {
							B[n] = '*';
						} else if (B[n] == '-' && B[n + 1] == '-') {
							B[n - 1] = '*';
						} else if (n > 1 && B[n - 2] == '-') {
							B[n + 1] = '-';
						} else if (n < N - 1 && B[n + 2] == '-') {
							B[n - 1] = '-';
						}
					} else if (A[n] == 2) {
						if (B[n - 1] == '-') {
							B[n] = '*';
							B[n + 1] = '*';
						} else if (B[n] == '-') {
							B[n - 1] = '*';
							B[n + 1] = '*';
						} else if (B[n + 1] == '-') {
							B[n - 1] = '*';
							B[n] = '*';
						} else if (B[n - 1] == '*' && B[n] == '*') {
							B[n + 1] = '-';
						} else if (B[n - 1] == '*' && B[n + 1] == '*') {
							B[n] = '-';
						} else if (B[n] == '*' && B[n + 1] == '*') {
							B[n - 1] = '-';
						}
					} else if (A[n] == 3) {
						B[n - 1] = '*';
						B[n] = '*';
						B[n + 1] = '*';
					}
				}
			}
			int cnt = 0;
			for (int n = 1; n <= N; n++) {
				if (B[n] == '*') cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
