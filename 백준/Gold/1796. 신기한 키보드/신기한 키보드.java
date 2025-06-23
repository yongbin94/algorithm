import java.util.*;

public class Main {
    static int res;
    static int[] cnt = new int[26], min = new int[26], max = new int[26];

    public static void main(String[] args) {
        String S = new Scanner(System.in).nextLine();
        Arrays.fill(min, Integer.MAX_VALUE);
        res = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            int v = S.charAt(i) - 'a';
            cnt[v]++;
            min[v] = Math.min(min[v], i);
            max[v] = Math.max(max[v], i);
        }
        recur(0, 0, 0);
        System.out.println(res);
    }

    private static void recur(int i, int v, int w) {
        if (v == 26) {
            res = Math.min(res, w);
            return;
        }
        int c = cnt[v];
        if (c == 0) {
            recur(i, v + 1, w);
            return;
        }
        int l = min[v];
        int r = max[v];
        if (l >= i) {
            recur(r, v + 1, w + r - i + c);
        } else if (r <= i) {
            recur(l, v + 1, w + i - l + c);
        } else {
            recur(l, v + 1, w + r - i + r - l + c);
            recur(r, v + 1, w + i - l + r - l + c);
        }
    }
}