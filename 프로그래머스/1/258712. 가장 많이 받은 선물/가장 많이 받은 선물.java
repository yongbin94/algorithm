import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        HashMap<String, Integer> hm = new HashMap<>();
        for (String friend : friends)
            hm.put(friend, hm.size());
        int[][] map = new int[N][N];
        int[] count = new int[N];
        int[] answer = new int[N];
        StringTokenizer st;
        for (String gift : gifts) {
            st = new StringTokenizer(gift);
            int a = hm.get(st.nextToken());
            int b = hm.get(st.nextToken());
            count[a]--;
            count[b]++;
            map[a][b]++;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map[i][j] == map[j][i]) {
                   if (count[i] < count[j])
                       answer[i]++;
                    else if (count[i] > count[j])
                       answer[j]++;
                    
                } else if (map[i][j] > map[j][i])
                       answer[i]++;
                else
                       answer[j]++;
            }
        }
        return Arrays.stream(answer).max().getAsInt();
    }
}