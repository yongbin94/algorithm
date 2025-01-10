import java.io.*;
import java.util.*;

public class Main {
	static int N, P, F, S, V, C;
	static List<Food> list;
	static boolean[] selected, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		C = Integer.MAX_VALUE;
		list = new ArrayList<>();
		selected = new boolean[N];
		min = new boolean[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		recur(0, 0, 0, 0, 0, 0);
		if(C == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		StringBuilder sb= new StringBuilder();
		sb.append(C).append("\n");
		for(int n = 0; n < N; n++)
			if(min[n])
				sb.append(n + 1).append(" ");
		System.out.println(sb);
		
	}

	private static void recur(int start, int p, int f, int s, int v, int c) {
		if (C <= c)
			return;
		if (P <= p && F <= f && S <= s && V <= v) {
			for (int n = 0; n < N; n++)
				min[n] = selected[n];
			C = c;
			return;
		}
		for (int n = start; n < N; n++) {
			selected[n] = true;
			Food food = list.get(n);
			recur(n + 1, p + food.p, f + food.f, s + food.s, v + food.v, c + food.c);
			selected[n] = false;
		}
	}

	private static class Food {
		int p, f, s, v, c;

		public Food(int p, int f, int s, int v, int c) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}
}