import java.io.*;
import java.util.*;

public class Main {
	static int[][][] A = { { { 0 }, { 0, 0, 0, 0 } }, { { 0, 0 } }, { { 0, 0, 1 }, { 1, 0 } },
			{ { 1, 0, 0 }, { 0, 1 } }, { { 0, 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 0, 1 } },
			{ { 0, 0, 0 }, { 2, 0 }, { 0, 1, 1 }, { 0, 0 } }, { { 0, 0, 0 }, { 0, 0 }, { 1, 1, 0 }, { 0, 2 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] map = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		for (int[] a : A[P - 1]) {
			for (int i = 0; i <= C - a.length; i++) {
				boolean isValid = true;
				for (int j = 0; j < a.length - 1; j++) {
					if (map[i + j + 1] - map[i + j] != a[j + 1] - a[j]) {
						isValid = false;
						break;
					}
				}
				if (isValid)
					res++;
			}
		}
		System.out.println(res);
	}
}
