import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		boolean[] used = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++)
			A[n] = Integer.parseInt(st.nextToken());
		int i = 0, j = 0;
		long result = 0;
		while (i < N) {
			if (j < N && !used[A[j]]) {
				used[A[j++]] = true;
			} else {
				result += j - i;
				used[A[i++]] = false;
			}
		}
		System.out.println(result);
	}
}
