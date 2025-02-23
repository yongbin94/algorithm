import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static List<Integer> A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int v = Integer.parseInt(st.nextToken());
			if (v > M) continue;
			A.add(v);
		}
		Collections.sort(A);
		int i = 0, j = A.size() - 1, answer = -1;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (check(mid)) {
				i = mid + 1;
				answer = mid;
			} else {
				j = mid - 1;
			}
		}

		System.out.println(answer + 1);
	}

	private static boolean check(int i) {
		int v = 0;
		while (i >= 0) {
			v += A.get(i);
			if (v > M) return false;
			i -= K;
		}
		return true;
	}
}