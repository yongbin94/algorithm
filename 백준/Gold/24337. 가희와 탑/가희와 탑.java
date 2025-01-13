import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int a = 1;
		sc.close();
		if (A + B - 1 > N) {
			System.out.println(-1);
			return;
		}
		if(A < B) A--;
		else B--;
		
		StringBuilder sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			if (A == 0 && n == 1)
				sb.append(B--).append(" ");
			else if(n <= N - A - B)
				sb.append(1).append(" ");
			else if (n <= N - B)
				sb.append(a++).append(" ");
			else
				sb.append(N - n + 1).append(" ");
		}
		System.out.println(sb);
	}
}
