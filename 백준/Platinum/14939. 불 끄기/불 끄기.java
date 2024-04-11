import java.io.*;
import java.util.*;

public class Main {
	static final int N = 10;
	static int count, result;
	static int[] org, tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		org = new int[N];
		for (int n = 0; n < N; n++)
			org[n] = Integer.parseInt(br.readLine().replace("#", "0").replace("O", "1"), 2);
		result = Integer.MAX_VALUE;
		bit : for (int bit = 0; bit < 1 << 10; bit++) {
			tmp = Arrays.copyOf(org, N);
			count = 0;
			for (int i = 0; i < N; i++)
				if (((bit >> i) & 1) == 1)
					turn(0, i);
			for (int n = 1; n < N; n++) {
				for(int i = 0; i < N; i++)
					if(((tmp[n-1] >> i) & 1) == 1)
						if(turn(n, i))
							continue bit;
			}
			if(tmp[N-1] == 0)
				result = Math.min(result, count);
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static boolean turn(int r, int c) {
		tmp[r] ^= 1 << c;
		if(c + 1 < N)
			tmp[r] ^= 1 << (c + 1);
		if(c - 1 >= 0)
			tmp[r] ^= 1 << (c - 1);
		if(r + 1 < N)
			tmp[r + 1] ^= 1 << c;
		if(r - 1 >= 0)
			tmp[r - 1] ^= 1 << c;
		return ++count >= result;
	}
}
