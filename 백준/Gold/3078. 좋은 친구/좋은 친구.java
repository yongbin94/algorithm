import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] A = new int[21];
		long res = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int n = 0; n < N; n++) {
			if (q.size() > K) A[q.poll()]--;
			int len = br.readLine().length();
			q.offer(len);
			res += A[len]++;
		}
		System.out.println(res);
	}
}