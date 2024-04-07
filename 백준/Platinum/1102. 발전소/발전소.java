import java.io.*;
import java.util.*;

public class Main {
    static int N, P, result;
    static int[] DP;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[1 << N];
        Arrays.fill(DP, Integer.MAX_VALUE);
        map = new int[N][];
        for (int n = 0; n < N; n++)
            map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        char[] cArr = br.readLine().toCharArray();
        int bit = 0;
        for (char c : cArr)
            bit = (bit << 1) | (c == 'Y' ? 1 : 0);
        P = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        solution(bit);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void solution(int bit) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(bit);
        if(Integer.bitCount(bit) >= P) {
            result = 0;
            return;
        }
        DP[bit] = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            List<Integer> iList = new ArrayList<>();
            List<Integer> jList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (((curr >> (N - 1 - i)) & 1) == 1)
                    iList.add(i);
                else
                    jList.add(i);
            }
            for(int j : jList) {
                int min = Integer.MAX_VALUE;
                for(int i : iList)
                    min = Math.min(min, map[i][j]);
                int next = curr | (1 << (N - 1 - j));
                if(DP[next] <= DP[curr] + min)
                    continue;
                DP[next] = DP[curr] + min;
                if(Integer.bitCount(next) == P) {
                    result = Math.min(result, DP[next]);
                    continue;
                }
                q.offer(next);
            }
        }
    }
}
