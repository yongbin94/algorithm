import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static long H, W;
    static Map<Long, Integer> map = new HashMap<>();
    static Set<Long> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Long.parseLong(st.nextToken());
        W = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            long h = Integer.parseInt(st.nextToken());
            long w = Integer.parseInt(st.nextToken());
            map.put(h * W + w, map.getOrDefault(h * W + w, 0) + 1);
        }
        for(long i : map.keySet())
            solution(i);
        System.out.println(answer);
    }

    private static void solution(long hs) {
        long h = hs / W;
        long w = hs % W;
        for(int r = -2; r <= 0; r++) {
            for(int c = -2; c <= 0; c++) {
                long nh = h + r;
                long nw = w + c;
                if(!isIn(nh, nw) || (nh + 2 > H || nw + 2 > W)) 
                    continue;
                long nhs = nh * W + nw;
                if(visited.contains(nhs))
                    continue;
                visited.add(nhs);
                findIchigo(nh, nw);
            }
        }
        
    }
    private static void findIchigo(long h, long w) {
        int count = 0;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                long nh = h + r;
                long nw = w + c;
                long nhs = nh * W + nw;
                count += map.getOrDefault(nhs, 0);
            }
        }
        answer = Math.max(answer, count);
    }

    private static boolean isIn(long h, long w) {
        return h >= 1 && h <= H && w >= 1 && w <= W;
    }
}