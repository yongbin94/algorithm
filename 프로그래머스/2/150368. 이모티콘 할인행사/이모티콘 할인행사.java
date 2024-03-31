class Solution {
    static int N, maxUser, maxAmount;
    static int[] discounts, emoticons;
    static int[][] users;
    public int[] solution(int[][] _users, int[] _emoticons) {
        users = _users;
        emoticons = _emoticons;
        N = emoticons.length;
        discounts = new int[N];
        maxUser = 0;
        maxAmount = 0;
        recur(0);
        int[] answer = {maxUser, maxAmount};
        return answer;
    }
    
    public static void recur(int cnt) {
        if(cnt == N) {
            int userCnt = 0;
            int total = 0;
            for(int[] user : users) {
                int tmp = 0;
                for(int i = 0; i < emoticons.length; i++) {                               if(user[0] <= discounts[i]) {
                        tmp += (emoticons[i] / 100) * (100 - discounts[i]);
                        if(tmp >= user[1]) {
                            tmp = 0;
                            userCnt++;
                            break;
                        }
                    }
                }
                total += tmp;
            }
            if(maxUser == userCnt) {
                maxAmount = Math.max(maxAmount, total);
            }
            if(maxUser < userCnt) {
                maxUser = userCnt;
                maxAmount = total;
            }          
            return;
        }
        
        for(int i = 0; i <= 40; i += 10) {
            discounts[cnt] = i;
            recur(cnt + 1);
        }
    }
}