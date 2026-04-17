import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<long[]> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long s = Long.parseLong(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			if (s > e) {
				list.add(new long[] { e, s });
			}
		}

		Collections.sort(list, (a, b) -> Long.compare(a[0], b[0]));

		long res = M;
		if (list.size() > 0) {
			long currS = list.get(0)[0];
			long currE = list.get(0)[1];

			for (int i = 1; i < list.size(); i++) {
				long nextS = list.get(i)[0];
				long nextE = list.get(i)[1];

				if (nextS <= currE) {
					currE = Math.max(currE, nextE);
				} else {
					res += (currE - currS) * 2;
					currS = nextS;
					currE = nextE;
				}
			}
			res += (currE - currS) * 2;
		}

		System.out.println(res);
	}
}