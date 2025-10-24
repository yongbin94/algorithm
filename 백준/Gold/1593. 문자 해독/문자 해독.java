import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int n = 0, answer = 0;
		int[] A = new int[52];
		String W = br.readLine();
		String S = br.readLine();
		for (int i = 0; i < g; i++) {
			if (A[get(W.charAt(i))]++ == 0) n++;
		}
		for (int i = 0; i < g; i++) {
			int a = get(S.charAt(i));
			if (--A[a] == 0) n--;
			else if (A[a] == -1) n++;
		}
        if (n == 0) answer++; 
		for (int i = g; i < s; i++) {
			int a = get(S.charAt(i));
			int b = get(S.charAt(i - g));
            if (--A[a] == 0) n--;
            else if (A[a] == -1) n++;
            if (++A[b] == 0) n--;
            else if (A[b] == 1) n++;
			if (n == 0) answer++;
		}
		System.out.println(answer);
	}

	private static int get(char ch) {
		int res = ch - 'A';
		if (res > 26)
			res -= 6;
		return res;
	}
}