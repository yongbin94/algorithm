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
		int a = 0, b = 0, min = Integer.MAX_VALUE;
		int i = 0;
		int j = N - 1;
		while (i < j) {
			int abs = Math.abs(A[i] + A[j]);
			if (min > abs) {
				min = abs;
				a = A[i];
				b = A[j];
			}
			if(A[i] + A[j] > 0)
				j--;
			else if(A[i] + A[j] < 0)
				i++;
			else 
				break;
		}
		System.out.println(a + " " + b);
	}
}