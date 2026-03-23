import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long M = Long.parseLong(st.nextToken());
		long N = Long.parseLong(st.nextToken());
		long count = 0;
		long r = 0, c = 0;
		if (M > N) {
			count = 2 * N - 1;
			if (N % 2 != 0) {
				r = N / 2 + 1 + (M - N);
				c = N / 2 + 1;
			} else {
				r = N / 2 + 1;
				c = N / 2;
			}
		} else {
			count = 2 * M - 2;
			if (M % 2 != 0) {
				r = M / 2 + 1;
				c = N - (M / 2);
			} else {
				r = M / 2 + 1;
				c = M / 2;
			}
		}
		System.out.printf("%d\n%d %d\n", count, r, c);
	}
}