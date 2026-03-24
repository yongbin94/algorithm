import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] ip = new int[4];
		int[] mask = new int[4];
		int[] addr = new int[4];

		StringTokenizer st = new StringTokenizer(br.readLine(), ".");
		for (int i = 0; i < 4; i++) {
			ip[i] = Integer.parseInt(st.nextToken());
		}

		int octet = 3;
		int bit = 8;

		while (N-- > 1) {
			st = new StringTokenizer(br.readLine(), ".");
			for (int i = 0; i <= octet; i++) {
				int v = Integer.parseInt(st.nextToken());

				if (ip[i] != v) {
					int common = 0;
					for (int b = 7; b >= 0; b--) {
						if (((ip[i] >> b) & 1) != ((v >> b) & 1)) break;
						common++;
					}

					if (i < octet || (i == octet && common < bit)) {
						octet = i;
						bit = common;
					}
					break;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i < octet) {
				addr[i] = ip[i];
				mask[i] = 255;
			} else if (i == octet) {
				int m = 0;
				for (int b = 7; b > 7 - bit; b--) {
					m |= (1 << b);
				}
				addr[i] = ip[i] & m;
				mask[i] = m;
			} else {
				addr[i] = 0;
				mask[i] = 0;
			}
		}

		System.out.printf("%d.%d.%d.%d\n", addr[0], addr[1], addr[2], addr[3]);
		System.out.printf("%d.%d.%d.%d\n", mask[0], mask[1], mask[2], mask[3]);
	}
}