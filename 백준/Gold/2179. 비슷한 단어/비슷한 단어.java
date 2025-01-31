import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = -1;
		int N = Integer.parseInt(br.readLine());
		List<Word> words = new ArrayList<>();
		for (int i = 0; i < N; i++)
			words.add(new Word(br.readLine(), i));
		Collections.sort(words, (o1, o2) -> o1.str.compareTo(o2.str));
		Word prev = words.get(0);
		PriorityQueue<Word> pq = new PriorityQueue<>((o1, o2) -> o1.idx - o2.idx);
		boolean[] used = new boolean[N];
		for (int n = 1; n < words.size(); n++) {
			Word curr = words.get(n);
			if (curr.str.equals(prev.str)) {
				prev = prev.idx < curr.idx ? prev : curr;
				continue;
			}
			if (Math.min(prev.str.length(), curr.str.length()) < cnt) {
				prev = curr;
				continue;
			}
			for (int i = 0; i <= prev.str.length(); i++) {
				if (i == prev.str.length() || prev.str.charAt(i) != curr.str.charAt(i)) {
					if (cnt > i)
						break;
					if (cnt < i) {
						pq = new PriorityQueue<>();
						used = new boolean[N];
					}
					cnt = i;
					if (!used[prev.idx]) {
						used[prev.idx] = true;
						pq.offer(prev);
					}
					used[curr.idx] = true;
					pq.offer(curr);
					break;
				}
			}
			prev = curr;
		}
		System.out.println(pq.peek().str);
		String S = pq.poll().str.substring(0, cnt);
		w: while (!pq.isEmpty()) {
			Word word = pq.poll();
			for (int i = 0; i < cnt; i++)
				if (word.str.charAt(i) != S.charAt(i))
					continue w;
			System.out.println(word.str);
			return;
		}
	}

	private static class Word implements Comparable<Word> {
		String str;
		int idx;

		public Word(String str, int idx) {
			this.str = str;
			this.idx = idx;
		}

		@Override
		public int compareTo(Word o) {
			return this.idx - o.idx;
		}
	}
}