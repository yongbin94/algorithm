import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		while (true) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (B[i] % 2 != 0) {
					B[i]--;
					answer++;
				}
				if (B[i] == 0)cnt++;
			}

			if (cnt == N) break;

			for (int i = 0; i < N; i++) {
				B[i] /= 2;
			}
			answer++;
		}

		System.out.println(answer);
	}
}