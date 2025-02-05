import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Map<String, List<String>> hm = new HashMap<>();
		hm.put("Baba", new ArrayList<>());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			st.nextToken();
			String b = st.nextToken();
			if (!hm.containsKey(a))
				hm.put(a, new ArrayList<>());
			hm.get(a).add(b);
		}
		TreeSet<String> ts = new TreeSet<>();
		Queue<String> q = new ArrayDeque<>();
		q.add("Baba");
		while (!q.isEmpty()) {
			String a = q.poll();
			if (!hm.containsKey(a))
				continue;
			for (String b : hm.get(a)) {
				if (ts.contains(b))
					continue;
				ts.add(b);
				q.add(b);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String a : ts) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}
}
