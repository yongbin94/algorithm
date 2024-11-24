import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A, B;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N * 2];
		selected = new boolean[N];
		Arrays.fill(B, -1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(A);
		if(!recur(0))
			System.out.println(-1);
	}

	private static boolean recur(int cnt) {
		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for(int v : B)
				sb.append(v).append(" ");
			System.out.println(sb);
			return true;
		}

		for (int n = 0; n < N; n++) {
			if (selected[n])
				continue;
			selected[n] = true;
			for (int m = 0; m < N * 2; m++) {
				if (A[n] + m + 1 >= N * 2)
					break;
				if (B[m] == -1) {
					if (B[A[n] + m + 1] != -1)
						break;
					B[m] = A[n];
					B[A[n] + m + 1] = A[n];
					if(recur(cnt + 1))
						return true;
					B[m] = -1;
					B[A[n] + m + 1] = -1;
					break;
				}
			}
			selected[n] = false;
		}
		return false;
	}
}