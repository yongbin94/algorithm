import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int K = new Scanner(System.in).nextInt();

		while (K > 0) {
			if (K % 2 == 1) {
				sb.append("4");
				K = (K - 1) / 2;
			} else {
				sb.append("7");
				K = (K - 2) / 2;
			}
		}
		System.out.println(sb.reverse());
	}
}