import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double X = Integer.parseInt(st.nextToken());
		double Y = Integer.parseInt(st.nextToken());
		double D = Integer.parseInt(st.nextToken());
		double T = Integer.parseInt(st.nextToken());
		double d = Math.hypot(X, Y);
		int n = (int) Math.floor(d / D);
		double answer = 0;
		if (d < D) answer = Math.min(d, Math.min(T + (D - d), T * 2));
		else answer = Math.min(d, Math.min(n * T + (d - n * D), (n + 1) * T));
		System.out.printf("%.10f", answer);
	}
}
