import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer>[] Q = new ArrayDeque[101];
		boolean[] plugged = new boolean[101];
		int[] plug = new int[N];
		for (int k = 1; k <= 100; k++)
			Q[k] = new ArrayDeque<>();
		int[] input = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		int index = 0;
		for (int i = 1; i <= N; i++) {
			if(index >= K)
				break;
			index++;
			if (plugged[input[index]]) {
				i--;
				continue;
			}
			plug[i-1] = input[index];
			plugged[input[index]] = true;
		}
		int result = 0;
		for (int k = index; k <= K; k++)
			Q[input[k]].add(k);

		outer: while (index <= K) {
			int item = input[index];
			// 1. 이미 꽃혀 있으면 넘어감
			if (plugged[item]) {
				Q[item].poll();
				index++;
				continue outer;
			}
			// 2. 뽑을 콘센트 찾기.
			// 후보 1. 다시 사용되지 않을 경우
			// 후보 2. 가장 나중에 사용되는 경우
			int plugIdx = -1;
			int maxIdx = 0;
			for (int i = 0; i < plug.length; i++) {
				if (Q[plug[i]].isEmpty()) { // 후보 1
					plugged[plug[i]] = false;
					plugged[item] = true;
					plug[i] = item;
					Q[item].poll();
					index++;
					result++;
					continue outer;
				}
				if(maxIdx < Q[plug[i]].peek()) {
					maxIdx = Q[plug[i]].peek();
					plugIdx = i;
				}
			}
			plugged[plug[plugIdx]] = false;
			plugged[item] = true;
			plug[plugIdx] = item;
			Q[item].poll();
			result++;
			index++;
		}
		
		System.out.println(result);
	}
}
