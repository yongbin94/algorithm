import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		int K = Integer.parseInt(br.readLine());
		int L = A.length - K;
		int answer = 0;
		for (int i = 0; i < L; i++) {
			int[] C = new int[26];
			for (int j = i; j < A.length; j += L)
				C[A[j] - 'a']++;
			answer += Arrays.stream(C).sum() - Arrays.stream(C).max().getAsInt();
		}
		System.out.println(answer);
	}
}