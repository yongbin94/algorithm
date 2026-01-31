import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = 0;
		long l = 3;
		while (l < N) {
			k++;
			l = l * 2 + (k + 3);
		}

		recur(N, k, l);
	}

	private static void recur(int n, int k, long l) {
		long prev = (l - (k + 3)) / 2;
		if (n <= prev) {
			recur(n, k - 1, prev);
		} else if (n > prev + (k + 3)) {
			recur((int) (n - (prev + (k + 3))), k - 1, prev);
		} else {
			System.out.println(n - prev == 1 ? "m" : "o");
		}
	}
}
