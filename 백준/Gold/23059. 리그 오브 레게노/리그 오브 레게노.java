import java.io.*;
import java.util.*;

public class Main {
	static Map<String, Integer> map;
	static List<String> names;
	static List<List<Integer>> edges;
	static List<Integer> in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		names = new ArrayList<>();
		edges = new ArrayList<>();
		in = new ArrayList<>();
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			if (!map.containsKey(A)) {
				map.put(A, map.size());
				names.add(A);
				edges.add(new ArrayList<>());
				in.add(0);
			}
			if (!map.containsKey(B)) {
				map.put(B, map.size());
				names.add(B);
				edges.add(new ArrayList<>());
				in.add(0);
			}
			int a = map.get(A);
			int b = map.get(B);
			edges.get(a).add(b);
			in.set(b, in.get(b) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> names.get(o1).compareTo(names.get(o2)));
		for (int i = 0; i < names.size(); i++) {
			if (in.get(i) > 0) continue;
			pq.offer(i);
		}

		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (!pq.isEmpty()) {
			PriorityQueue<Integer> tmp = new PriorityQueue<>((o1, o2) -> names.get(o1).compareTo(names.get(o2)));
			while (!pq.isEmpty()) {
				int u = pq.poll();
				sb.append(names.get(u)).append("\n");
				for (int v : edges.get(u)) {
					in.set(v, in.get(v) - 1);
					if (in.get(v) > 0) continue;
					tmp.offer(v);
				}
				cnt++;
			}
			pq = tmp;
		}
		System.out.println(names.size() != cnt ? -1 : sb);
	}
}