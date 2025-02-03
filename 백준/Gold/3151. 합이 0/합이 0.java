import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] cnt = new int[20001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int v = Integer.parseInt(st.nextToken());
			cnt[v + 10000]++;
			A[n] = v;
		}
		long answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int v = 0 - A[i] - A[j];
				if (v < -10000 || v > 10000)
					continue;
				answer += cnt[v + 10000];
				if (A[i] == v)
					answer--;
				if (A[j] == v)
					answer--;
			}
		}
		System.out.println(answer / 3);
	}
}
