import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long l = 0, r = (long) n * Arrays.stream(times).max().getAsInt();
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (check(mid, n, times)) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    static boolean check(long v, int n, int[] times) {
        long res = 0;
        for (int time : times) {
            res += v / time;
            if (res >= n) return true;
        }
        return res >= n;
    }
}