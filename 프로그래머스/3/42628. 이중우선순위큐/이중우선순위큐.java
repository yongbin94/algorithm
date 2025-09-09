import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        TreeSet<Integer> ts = new TreeSet<>();
        for (String str : operations) {
            StringTokenizer st = new StringTokenizer(str);
            char c = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());
            if (c == 'I') {
                ts.add(v);
            } else {
                if (v == 1) {
                    ts.pollLast();
                } else {
                    ts.pollFirst();
                }
            }
        }
        if (!ts.isEmpty()) {
            answer[0] = ts.last();
            answer[1] = ts.first();
        }
        return answer;
    }
}