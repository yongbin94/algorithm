import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int width = 0;
		st = new StringTokenizer(br.readLine());
		while (N-- > 0)
			width += Integer.parseInt(st.nextToken());
		
		if(width == M)
			System.out.println(0);
		else if(width > M)
			System.out.println(-1);
		else {
			if(width >= L)
				System.out.println(1);
			else if(width + L <= M)
				System.out.println(1);
			else
				System.out.println(-1);
		}
	}
}
