import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        long[] A = new long[50001];
        int i = 0;
        for (int a : works) {
            A[a]++;
            i = Math.max(i, a);
        }
        int cnt = 0;
        long sum = 0;
        while (i >= 0) {
            if (sum + cnt + A[i] > n) break;
            cnt += A[i];
            sum += cnt;
            i--;
        }
        if (i > 0) {
            answer += (i - 1) * (i - 1) * (n - sum);
            answer += i * i * (cnt + A[i] - (n - sum));
            while (i-- > 0) {
                answer += i * i * A[i];
            }
        }       
        return answer;
    }
}