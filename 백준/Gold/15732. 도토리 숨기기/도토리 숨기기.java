import java.io.*;
import java.util.*;

public class Main {
	static int N, K, D;
	static Rule[] R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		R = new Rule[K];
		for(int k = 0; k < K; k++) {
			R[k] = new Rule(new StringTokenizer(br.readLine()));
		}
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int l = 1, r = N;
		while(l < r) {
			int mid = (l + r) / 2;
			if(check(mid)) r = mid;
			else l = mid + 1;
		}
		return l;
	}

	private static boolean check(int v) {
		long cnt = 0;
		for (Rule r : R) {
			if (r.a > v) continue;
			int end = Math.min(r.b, v);
			cnt += (end - r.a) / r.c + 1;
			if (cnt >= D) return true;
		}
		return cnt >= D;
	}

	private static class Rule {
		int a, b, c;

		public Rule(StringTokenizer st) {
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
		}
	}
}