import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] v = new boolean[400001];
		int res = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				int target = A[i] - A[j] + 200000;
				if (target >= 0 && target <= 400000 && v[target]) {
					res++;
					break;
				}
			}
			for (int j = 0; j <= i; j++) {
				int sum = A[i] + A[j] + 200000;
				if (sum >= 0 && sum <= 400000) {
					v[sum] = true;
				}
			}
		}
		System.out.println(res);
	}
}