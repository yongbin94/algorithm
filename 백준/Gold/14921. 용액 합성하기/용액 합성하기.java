import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		for (int n = 0; n < N; n++)
			A[n] = Integer.parseInt(st.nextToken());

		int s = 0;
		int e = N - 1;
		int answer = Integer.MAX_VALUE;
		while (s < e) {
			if(Math.abs(answer) > Math.abs(A[e] + A[s]))
				answer = A[e] + A[s];
			if (Math.abs(A[e] + A[s + 1]) > Math.abs(A[e - 1] + A[s]))
				e--;
			else
				s++;
		}
		System.out.println(answer);
	}
}
