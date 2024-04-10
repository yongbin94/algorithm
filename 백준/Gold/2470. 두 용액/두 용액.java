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
		Arrays.sort(A);
		int a = 0, b = 0, min = Integer.MAX_VALUE;
		for (int v : A) {
			v *= -1;
			int i = Arrays.binarySearch(A, v);
			if (i < 0) {
				i = (i + 1) * -1;
				if (i == N || (i > 0 && Math.abs(v - A[i]) > Math.abs(v - A[i - 1])))
					i--;
			}
			if(A[i] == v * -1)
				continue;
			if (min > Math.abs(v - A[i])) {
				min = Math.abs(v - A[i]);
				a = v * -1;
				b = A[i];
			}
		}
		System.out.println(a < b ? a + " " + b : b + " " + a);
	}
}
