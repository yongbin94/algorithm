import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        StringTokenizer st;
        List<Integer>[][][][] list = new ArrayList[3][2][2][2];
        for (int i = 0; i < 3; i ++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        list[i][j][k][l] = new ArrayList<>();
        
        for (String tmp : info) {
            st = new StringTokenizer(tmp);
            char lan = st.nextToken().charAt(0);
            int a = lan == 'c' ? 0 : lan == 'j' ? 1 : 2;
            int b = st.nextToken().charAt(0) == 'b' ? 0 : 1;
            int c = st.nextToken().charAt(0) == 'j' ? 0 : 1;
            int d = st.nextToken().charAt(0) == 'c' ? 0 : 1;
            list[a][b][c][d].add(Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < 3; i ++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        Collections.sort(list[i][j][k][l]);
        
        for (int t = 0; t < query.length; t++) {
            String tmp = query[t];
            st = new StringTokenizer(tmp);
            char a = st.nextToken().charAt(0);
            st.nextToken();
            char b = st.nextToken().charAt(0);
            st.nextToken();
            char c = st.nextToken().charAt(0);
            st.nextToken();
            char d = st.nextToken().charAt(0);
            int e = Integer.parseInt(st.nextToken());
            
            int count = 0;
            for (int i = (a == 'p' ? 2 : a == 'j' ? 1 : 0); i <= (a == 'c' ? 0 : a == 'j' ? 1 : 2); i++) {
                for (int j = (b == 'f' ? 1 : 0); j <= (b == 'b' ? 0 : 1); j++) {
                    for (int k = (c == 's' ? 1 : 0); k <= (c == 'j' ? 0 : 1); k++) {
                        for (int l = (d == 'p' ? 1 : 0); l <= (d == 'c' ? 0 : 1); l++) {
                            int index = Collections.binarySearch(list[i][j][k][l], e);
                            if (index < 0)
                                index = -(index + 1);
                            while(index != 0 && list[i][j][k][l].get(index - 1) == e)
                                index--;
                            count += list[i][j][k][l].size() - index;
                        }
                    }
                }
            }
            answer[t] = count;
        }
        return answer;
    }
}