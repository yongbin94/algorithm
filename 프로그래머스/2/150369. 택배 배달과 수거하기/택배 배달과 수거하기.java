class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int i = n - 1, j = n - 1;
        while(i >= 0 && deliveries[i] == 0)
            i--;
        while(j >= 0 && pickups[j] == 0)
            j--;
        while(i >= 0 || j >= 0) {
            answer += Math.max(i + 1, j + 1) * 2;
            int c = cap;
            while(i >= 0 && c >= 0) {
                if(c < deliveries[i]) {
                    deliveries[i] -= c;
                    break;
                }
                c -= deliveries[i--];
            }
            c = cap;
            while(j >= 0 && c >= 0) {
                if(c < pickups[j]) {
                    pickups[j] -= c;
                    break;
                }
                c -= pickups[j--];
            }
        }
        return answer;
    }
}