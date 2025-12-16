import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int S = Integer.parseInt(br.readLine());

		int i = 0;

		while (i < N && S > 0) {
			int max = i;

			for (int j = i + 1; j < N && j - i <= S; j++) {
				if (A[j] > A[max]) max = j;
			}

			for (int j = max; j > i; j--) {
				int tmp = A[j];
				A[j] = A[j - 1];
				A[j - 1] = tmp;
			}

			S -= (max - i++);
		}

		StringBuilder sb = new StringBuilder();
		for (int a : A) {
			sb.append(a).append(" ");
		}
		System.out.println(sb);
	}
}
