import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] A = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			String input = br.readLine();
			for (int c = 1; c <= M; c++) {
				A[r][c] = ((input.charAt(c - 1) == 'B') == ((r + c) % 2 == 0)) ? 1 : 0;
				A[r][c] += A[r - 1][c] + A[r][c - 1] - A[r - 1][c - 1];
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int r = 0; r <= N - K; r++) {
			for (int c = 0; c <= M - K; c++) {
				int v = A[r + K][c + K] - A[r][c + K] - A[r + K][c] + A[r][c];
				answer = Math.min(answer, Math.min(v, K * K - v));
			}
		}
		System.out.println(answer);
	}
}