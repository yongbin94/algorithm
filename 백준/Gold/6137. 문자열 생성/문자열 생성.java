import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] A = new char[N];
		for (int n = 0; n < N; n++) {
			A[n] = br.readLine().charAt(0);
		}

		StringBuilder sb = new StringBuilder();
		int l = 0, r = N - 1, size = 0;
		while (l <= r) {
			if (A[l] == A[r]) {
				int i = 0;
				while (l + i <= r) {
					if (A[l + i] < A[r - i]) {
						sb.append(A[l++]).append(++size % 80 == 0 ? "\n" : "");
						break;
					} else if (A[l + i] > A[r - i]) {
						sb.append(A[r--]).append(++size % 80 == 0 ? "\n" : "");
						break;
					}
					i++;
				}

				if (l + i > r) {
					sb.append(A[l++]).append(++size % 80 == 0 ? "\n" : "");
				}
			} else if (A[l] < A[r]) {
				sb.append(A[l++]).append(++size % 80 == 0 ? "\n" : "");
			} else {
				sb.append(A[r--]).append(++size % 80 == 0 ? "\n" : "");
			}
		}
		System.out.print(sb);
	}
}