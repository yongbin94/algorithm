import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = N, tmp = 0;
		for (int n = 1; n <= N; n++) {
			if (Integer.parseInt(st.nextToken()) == 1) tmp = n;
            else answer = Math.max(answer, N + n - 2 * tmp);
		}
		System.out.println(answer);
	}
}