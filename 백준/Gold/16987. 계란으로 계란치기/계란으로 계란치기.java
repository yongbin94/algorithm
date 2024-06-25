import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Egg {
		int s, w;
		Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}

	static int N, result;
	static int[] hp;
	static Egg[] eggs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		hp = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		result = 0;
		recur(0);
		System.out.println(result);
	}

	private static void recur(int cnt) {
		if (cnt == N) {
			int count = 0;
			for(int i = 0; i < N; i++) {
				if(eggs[i].s <= hp[i])
					count++;
			}
			result = Math.max(result, count);
			return;
		}
		if (eggs[cnt].s <= hp[cnt]) {
			recur(cnt + 1);
			return;
		}
		boolean hasEgg = false;
		for (int i = 0; i < N; i++) {
			if (cnt == i || eggs[i].s <= hp[i])
				continue;
			hasEgg = true;
			hp[i] += eggs[cnt].w;
			hp[cnt] += eggs[i].w;
			recur(cnt + 1);
			hp[i] -= eggs[cnt].w;
			hp[cnt] -= eggs[i].w;
		}
		if(!hasEgg)
			recur(cnt + 1);
	}

}
